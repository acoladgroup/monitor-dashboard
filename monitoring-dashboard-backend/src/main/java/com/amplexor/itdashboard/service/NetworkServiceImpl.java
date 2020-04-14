package com.amplexor.itdashboard.service;

import com.amplexor.itdashboard.dao.NetworkInfoDao;
import com.amplexor.itdashboard.model.*;
import com.amplexor.itdashboard.model.rest.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.commons.lang3.time.DateUtils;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class NetworkServiceImpl implements NetworkService {

    @Value("${checkmk.url}")
    private String checkMKUrl;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NetworkInfoDao networkInfoDao;

    @Override
    public Optional<NetworkInfoResponse> getNetworkInfo() throws  Exception {
        NetworkInfoResponse networkInfoResponse = new NetworkInfoResponse();
        List<NetworkInfo> networkInfoList = networkInfoDao.findAll();
        networkInfoResponse.setNetworkInfoList(networkInfoList);
        return Optional.ofNullable(networkInfoResponse);
    }

    @Override
    public Optional<NetworkInfoResponse> getRealTimeNetworkInfo() throws  Exception {
        return retrieveNetworkInfo();
    }

    public Optional<NetworkInfoResponse> retrieveNetworkInfo() throws  Exception {
        return convertToInternalNetworkInfo(getExternalNetworkInfo());
    }

    static void assignIds(List<NetworkInfo> objects) {
        PrimitiveIterator.OfInt ids = IntStream.range(0, objects.size()).iterator();
        objects.stream().forEach(e -> ((NetworkInfo)e).setId(ids.next().longValue()));
    }

    public void retrieveAndSaveNetworkInfo() throws  Exception {
        Optional<NetworkInfoResponse> networkInfoResponse = convertToInternalNetworkInfo(getExternalNetworkInfo());

        if(networkInfoResponse.isPresent()) {
            assignIds(networkInfoResponse.get().getNetworkInfoList());
            networkInfoDao.deleteAll();
            networkInfoDao.saveAll(networkInfoResponse.get().getNetworkInfoList());
        }
    }

    public CountryNetworkInfo[] getExternalNetworkInfo() throws  Exception {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(checkMKUrl + "/network_info");

        ResponseEntity<CountryNetworkInfo[]> responseEntity = restTemplate.getForEntity(builder.toUriString(), CountryNetworkInfo[].class);

        return responseEntity.getBody();
    }

    public Optional<NetworkInfoResponse> convertToInternalNetworkInfo(CountryNetworkInfo[] countryNetworkInfo) throws Exception {
        NetworkInfoResponse networkInfoResponse = new NetworkInfoResponse();

        List<CountryNetworkInfo> networkInfoList = Arrays.stream(countryNetworkInfo).collect(Collectors.toList()) ;

        Date currentRetrieveDate = new Date();

        for (CountryNetworkInfo currentCountry :networkInfoList) {
            for(SubsidiaryNetworkInfo currentSubsidiary : currentCountry.getSubsidiaries()){
                Optional<ServiceNetworkInfo> optional = null;
                NetworkInfo netInfo = new NetworkInfo();
                netInfo.setCountry(currentCountry.getCountry());
                netInfo.setSubsidiaryLocation(currentSubsidiary.getName());
                netInfo.setRetrieveDate(currentRetrieveDate);

                optional = currentSubsidiary.getServices().stream()
                        .filter(x -> "MPLS".equals(x.getService()))
                        .findFirst();

                if(optional.isPresent()) {//Check whether optional has element you are looking for
                    ServiceNetworkInfo p = optional.get();//get it from optional
                    netInfo.setMplsStatus(p.getState());
                    netInfo.setMplsDownloadSpeed(p.getDownload());
                    netInfo.setMplsUploadSpeed(p.getUpload());
                }

                optional = currentSubsidiary.getServices().stream()
                        .filter(x -> "ISP1".equals(x.getService()))
                        .findFirst();

                if(optional.isPresent()) {//Check whether optional has element you are looking for
                    ServiceNetworkInfo p = optional.get();//get it from optional
                    netInfo.setPrimaryISPStatus(p.getState());
                    netInfo.setPrimaryIPSDownloadSpeed(p.getDownload());
                    netInfo.setPrimaryISPUploadSpeed(p.getUpload());
                }

                optional = currentSubsidiary.getServices().stream()
                        .filter(x -> "ISP2".equals(x.getService()))
                        .findFirst();

                if(optional.isPresent()) {//Check whether optional has element you are looking for
                    ServiceNetworkInfo p = optional.get();//get it from optional
                    netInfo.setSecondaryISPStatus(p.getState());
                    netInfo.setSecondaryISPDownloadSpeed(p.getDownload());
                    netInfo.setSecondaryISPUploadSpeed(p.getUpload());
                }

                optional = currentSubsidiary.getServices().stream()
                        .filter(x -> "MPLS SG".equals(x.getService()))
                        .findFirst();

                if(optional.isPresent()) {//Check whether optional has element you are looking for
                    ServiceNetworkInfo p = optional.get();//get it from optional
                    netInfo.setMplsSGStatus(p.getState());
                    netInfo.setMplsSGDownloadSpeed(p.getDownload());
                    netInfo.setMplsSGUploadSpeed(p.getUpload());
                }

                optional = currentSubsidiary.getServices().stream()
                        .filter(x -> "SDWAN".equals(x.getService()))
                        .findFirst();

                if(optional.isPresent()) {//Check whether optional has element you are looking for
                    ServiceNetworkInfo p = optional.get();//get it from optional
                    netInfo.setSdwanStatus(p.getState());
                    netInfo.setSdwanDownloadSpeed(p.getDownload());
                    netInfo.setSdwanUploadSpeed(p.getUpload());
                }

                networkInfoResponse.getNetworkInfoList().add(netInfo);
            }
        }

        return Optional.ofNullable(networkInfoResponse);
    }


    public static List<NetworkServices> convertOutputToNetworkServices(List<NetworkInfo> networkInfoList) throws  Exception {
        List<NetworkServices> networkServices = new ArrayList<NetworkServices>();
        Optional<NetworkInfo> optional = null;
        NetworkServices mplsNetworkService = new NetworkServices();
        mplsNetworkService.setName("MPLS");
        NetworkServices primaryISPService = new NetworkServices();
        primaryISPService.setName("ISP1");
        NetworkServices secondaryISPService = new NetworkServices();
        secondaryISPService.setName("ISP2");
        NetworkServices sdWanNetworkService = new NetworkServices();
        sdWanNetworkService.setName("SDWAN");

        //List of Subsidiary Names
        //this is a tupple that contains
        // first position the subsidiary name on the CheckMK api configuration
        // second position the name without special characters and captalized - this name will be used to compose the setterName on com.amplexor.itdashboard.model.NetworkServices
        // set + subsidiary[1] + Status
        // set + subsidiary[1] + DownloadSpeed
        // set + subsidiary[1] + UploadSpeed
        // create the method on the class and create the entry on the subsidiary list
        String[][] subsidiaryList = {
                {"Heverlee","heverlee"},
                {"Montreal","montreal"},
                {"Shanghai","shanghai"},
                {"Suzhou","suzhou"},
                {"Zagreb","zagreb"},
                {"Brest","brest"},
                {"Carquefou","carquefou"},
                {"Cherbourg Octeville","cherbourgOcteville"},
                {"Cherbourg La Hague","cherbourgLaHague"},
                {"Cherbourg La Saline","cherbourgLaSaline"},
                {"Lanester","lanester"},
                {"L Ardoise","lArdoise"},
                {"Lyon","lyon"},
                {"Montigny","montigny"},
                {"Toulouse","toulouse"},
                {"Augsburg","augsburg"},
                {"Berlin","berlin"},
                {"Düsseldorf","dusseldorf"},
                {"Chennai","chennai"},
                {"Riga","riga"},
                {"Bertrange","bertrange"},
                {"Krakow","krakow"},
                {"Paço de Arcos","pacoDarcos"},
                {"Bucharest","bucharest"},
                {"Cluj","cluj"},
                {"Sibiu","sibiu"},
                {"Novo Mesto","novoMesto"},
                {"Madrid","madrid"},
                {"Vitoria","vitoria"},
                {"Kreuzlingen","kreuzlingen"},
                {"Zurich","zurich"},
                {"Westminster","westminster"},
                {"River Falls","riverfalls"}
        };

        Class netowrkServicesClass = Class.forName("com.amplexor.itdashboard.model.NetworkServices");

        for (String[] subsidiary: subsidiaryList) {

            optional =  networkInfoList
                    .stream()
                    .filter(x -> subsidiary[0].equals(x.getSubsidiaryLocation()))
                    .findFirst();

            if(optional.isPresent()) {//Check whether optional has element you are looking for
                NetworkInfo p = optional.get();//get it from optional

                String s1 = subsidiary[1].substring(0, 1).toUpperCase();
                String nameCapitalized = s1 + subsidiary[1].substring(1);

                String statusSetMethodName = "set" + nameCapitalized + "Status";
                String downloadSpeedSetMethodName = "set" + nameCapitalized + "DownloadSpeed";
                String uploadSpeedSetMethodName = "set" + nameCapitalized + "UploadSpeed";
                String percentageHtmlMethodName = "set" + nameCapitalized + "PercentageHtml";

                Method setStatus = netowrkServicesClass.getDeclaredMethod(statusSetMethodName, String.class);
                Method setDownloadSpeed = netowrkServicesClass.getDeclaredMethod(downloadSpeedSetMethodName, String.class);
                Method setUploadSpeed = netowrkServicesClass.getDeclaredMethod(uploadSpeedSetMethodName, String.class);
                Method setPercentageHtml = netowrkServicesClass.getDeclaredMethod(percentageHtmlMethodName, String.class);

                setStatus.invoke(mplsNetworkService,p.getMplsStatus()) ;
                setDownloadSpeed.invoke(mplsNetworkService,p.getMplsDownloadSpeed()) ;
                setUploadSpeed.invoke(mplsNetworkService,p.getMplsUploadSpeed()) ;
                setPercentageHtml.invoke(mplsNetworkService,getPercentageHtml(p.getMplsDownloadSpeed(),p.getMplsUploadSpeed())) ;

                setStatus.invoke(primaryISPService,p.getPrimaryISPStatus()) ;
                setDownloadSpeed.invoke(primaryISPService,p.getPrimaryIPSDownloadSpeed()) ;
                setUploadSpeed.invoke(primaryISPService,p.getPrimaryISPUploadSpeed()) ;
                setPercentageHtml.invoke(primaryISPService,getPercentageHtml(p.getPrimaryIPSDownloadSpeed(),p.getPrimaryISPUploadSpeed())) ;

                setStatus.invoke(secondaryISPService,p.getSecondaryISPStatus()) ;
                setDownloadSpeed.invoke(secondaryISPService,p.getSecondaryISPDownloadSpeed()) ;
                setUploadSpeed.invoke(secondaryISPService,p.getSecondaryISPUploadSpeed()) ;
                setPercentageHtml.invoke(secondaryISPService,getPercentageHtml(p.getSecondaryISPDownloadSpeed(),p.getSecondaryISPUploadSpeed())) ;

                setStatus.invoke(sdWanNetworkService,p.getSdwanStatus()) ;
                setDownloadSpeed.invoke(sdWanNetworkService,p.getSdwanDownloadSpeed()) ;
                setUploadSpeed.invoke(sdWanNetworkService,p.getSdwanUploadSpeed()) ;
                setPercentageHtml.invoke(sdWanNetworkService,getPercentageHtml(p.getSdwanDownloadSpeed(),p.getSdwanUploadSpeed())) ;

                mplsNetworkService.setRetrieveDate(p.getRetrieveDate());
                primaryISPService.setRetrieveDate(p.getRetrieveDate());
                secondaryISPService.setRetrieveDate(p.getRetrieveDate());
                sdWanNetworkService.setRetrieveDate(p.getRetrieveDate());

                boolean hasDelay = false;
                if (p.getRetrieveDate() != null && DateUtils.addMinutes(p.getRetrieveDate(), 10).before(new Date()) ) {
                    hasDelay =  true;
                }

                mplsNetworkService.setHasDelay(hasDelay);
                primaryISPService.setHasDelay(hasDelay);
                secondaryISPService.setHasDelay(hasDelay);
                sdWanNetworkService.setHasDelay(hasDelay);

            }
        }

        networkServices.add(sdWanNetworkService);
        networkServices.add(mplsNetworkService);
        networkServices.add(primaryISPService);
        networkServices.add(secondaryISPService);


        return networkServices;
    }

    private static String getPercentageHtml(String downloadSpeed, String uploadSpeed) {
        int firstBracket;
        String contentOfBrackets;
        String retDownloadSpeed = null;
        String retUploadSpeed = null;
        if (downloadSpeed!= null) {
            firstBracket = downloadSpeed.indexOf('(');
            if (firstBracket > 0) {
                contentOfBrackets = downloadSpeed.substring(firstBracket + 1, downloadSpeed.indexOf(')', firstBracket));
                int pos = contentOfBrackets.indexOf("/");
                if (pos != -1) {
                    contentOfBrackets = contentOfBrackets.substring(0 , pos);
                }
                retDownloadSpeed = "↓" + contentOfBrackets + "\n";
            }
        }

        if (uploadSpeed!= null) {
            firstBracket = uploadSpeed.indexOf('(');
            if (firstBracket > 0) {
                contentOfBrackets = uploadSpeed.substring(firstBracket + 1, uploadSpeed.indexOf(')', firstBracket));
                int pos = contentOfBrackets.indexOf("/");
                if (pos != -1) {
                    contentOfBrackets = contentOfBrackets.substring(0 , pos);
                }
                retUploadSpeed = "↑" + contentOfBrackets;
            }
        }

        if(retDownloadSpeed != null && retUploadSpeed != null ) {
            return retDownloadSpeed + retUploadSpeed ;
        } else if (retDownloadSpeed == null && retUploadSpeed != null) {
            return retUploadSpeed;
        } else if (retDownloadSpeed != null && retUploadSpeed == null) {
            return retDownloadSpeed;
        } else {
            return "\n\n";
        }
    }
}
