export interface DatabaseStatusResponse {
  databaseUp : number,
  databaseDown : number,
  databaseUnreachable : number,
  databaseUnknown: number,
  databaseAgentDown: number,
  databaseMetricError: number,
  databaseBlackout: number,
  databaseStatusList: DatabaseStatus[]
}

export interface DatabaseStatus {
  instanceName : string;
  hostName : string;
  databaseStatus : string;
  totalMemory : string;
  databaseVersion : string;
  dataGuardStatus : string;
}

export interface BackupStatusResponse {
  backupCompleted : number,
  backupFailed : number,
  backupCompletedWithWarnings : number,
  backupRunning: number,
  backupStatusList: BackupStatus[]
}

export interface BackupStatus {
  lifeCycleStatus : string;
  lineOfBusiness : string;
  targetName : string;
  backupType : string;
  startTime : Date;
  status : string;
  timeTaken : string;
  inputSize : string;
  outputSize : string;
  daysAgo : string;
  day : string;
  collectionTime : Date;
}

export interface TableSpace {
  databaseName : string;
  databaseType : string;
  collectionTime : Date;
  tableSpace: String;
  percentageUsed: String;
}

export interface DashboardResponse {
  applicationServicesList: Services[]
  sharedServicesList: Services[]
  networkInfoList : NetworkInfo[]
}

export interface Services {
  targetName : string;
  status : string;
  startTimestamp : Date;
  comments: String;
  link: String;
}

export interface NetworkInfo {
  country: String;
  subsidiaryLocation: String;
  mplsStatus: String;
  mplsDownloadSpeed: String;
  mplsUploadSpeed: String;
  primaryISPStatus: String;
  primaryIPSDownloadSpeed: String;
  primaryISPUploadSpeed: String;
  secondaryISPStatus:String;
  secondaryISPDownloadSpeed: String;
  secondaryISPUploadSpeed: String;
  mplsSGStatus: String;
  mplsSGDownloadSpeed: String;
  mplsSGUploadSpeed: String;
}

export interface NetworkInfoResponse {
  networkInfoList : NetworkInfo[];
}

export interface NetworkService {
  name : String;
  heverleeStatus : String;
  heverleeDownloadSpeed : String;
  heverleeUploadSpeed : String;
  montrealStatus : String;
  montrealDownloadSpeed : String;
  montrealUploadSpeed : String;
}

export interface Profile {
  UserProfile : UserProfile;
}

export interface UserProfile {
  userId: number;
  firstName: string;
  lastName: string;
  login: string;
  phoneNumber:String;
}

export interface User {
  userId: number;
  firstName: string;
  lastName: string;
  login: string;
}

export interface Status {
  message: string;
  details: string;
  code: string;
}