#!/usr/bin/python
# -*- encoding: utf-8; py-indent-offset: 4 -*-
# +------------------------------------------------------------------+
# |             ____ _               _        __  __ _  __           |
# |            / ___| |__   ___  ___| | __   |  \/  | |/ /           |
# |           | |   | '_ \ / _ \/ __| |/ /   | |\/| | ' /            |
# |           | |___| | | |  __/ (__|   <    | |  | | . \            |
# |            \____|_| |_|\___|\___|_|\_\___|_|  |_|_|\_\           |
# |                                                                  |
# | Copyright Mathias Kettner 2014             mk@mathias-kettner.de |
# +------------------------------------------------------------------+
#
# This file is part of Check_MK.
# The official homepage is at http://mathias-kettner.de/check_mk.
#
# check_mk is free software;  you can redistribute it and/or modify it
# under the  terms of the  GNU General Public License  as published by
# the Free Software Foundation in version 2.  check_mk is  distributed
# in the hope that it will be useful, but WITHOUT ANY WARRANTY;  with-
# out even the implied warranty of  MERCHANTABILITY  or  FITNESS FOR A
# PARTICULAR PURPOSE. See the  GNU General Public License for more de-
# tails. You should have  received  a copy of the  GNU  General Public
# License along with GNU Make; see the file  COPYING.  If  not,  write
# to the Free Software Foundation, Inc., 51 Franklin St,  Fifth Floor,
# Boston, MA 02110-1301 USA.
from __future__ import division
from flask import Flask,jsonify
import os
import sys
import livestatus
import json
import time

start = time.time()
app = Flask(__name__)

omd_root = "/opt/omd/sites/lu01cmk"
socket_path = "unix:" + omd_root + "/tmp/run/live"

sites = {
  "lu01cmk" : {
        "socket"     : socket_path,
        "alias"      : "lu01cmk",
  },
  "ca01cmk" : {
        "alias"      : "ca01cmk",
        "socket"     : "tcp:192.168.212.41:6557",
        "nagios_url" : "/nagios/",
        "timeout"    : 10,
  },
  "cn01cmk" : {
        "alias"      : "cn01cmk",
        "socket"     : "tcp:172.25.138.30:6557",
        "nagios_url" : "/nagios/",
        "timeout"    : 10,
  },
  "de01cmk" : {
        "alias"      : "de01cmk",
        "socket"     : "tcp:172.25.169.31:6557",
        "nagios_url" : "/nagios/",
        "timeout"    : 10,
  },
  "fr03cmk" : {
        "alias"      : "fr03cmk",
        "socket"     : "tcp:192.168.218.136:6557",
        "nagios_url" : "/nagios/",
        "timeout"    : 10,
  },
  "fr20cmk" : {
        "alias"      : "fr20cmk",
        "socket"     : "tcp:192.168.220.60:6557",
        "nagios_url" : "/nagios/",
        "timeout"    : 10,
  },
  "fx01cmk" : {
        "alias"      : "fx01cmk",
        "socket"     : "tcp:172.25.232.87:6557",
        "nagios_url" : "/nagios/",
        "timeout"    : 10,
  },
  "in01cmk" : {
        "alias"      : "in01cmk",
        "socket"     : "tcp:172.25.136.22:6557",
        "nagios_url" : "/nagios/",
        "timeout"    : 10,
  },
  "pl01cmk" : {
        "alias"      : "pl01cmk",
        "socket"     : "tcp:172.25.130.96:6557",
        "nagios_url" : "/nagios/",
        "timeout"    : 10,
  },
  "po01cmk" : {
        "alias"      : "po01cmk",
        "socket"     : "tcp:172.25.190.32:6557",
        "nagios_url" : "/nagios/",
        "timeout"    : 10,
  },
  "ro02cmk" : {
        "alias"      : "ro02cmk",
        "socket"     : "tcp:172.25.184.87:6557",
        "nagios_url" : "/nagios/",
        "timeout"    : 10,
  },
  "si01cmk" : {
        "alias"      : "si01cmk",
        "socket"     : "tcp:172.19.100.169:6557",
        "nagios_url" : "/nagios/",
        "timeout"    : 10,
  }
}

network_matrix = [
  {
    "country": "Belgium",
    "subsidiaries": [
      {
        "name": "Heverlee",
        "services": [
          {
            "service": "MPLS",
            "service_name": "Interface 1",
            "host_name": "Belgium"
          },
          {
            "service": "ISP1",
            "service_name": "Interface 02",
            "host_name": "be01net-asa1",
			"sla_service_name": "ISP1_Belgium"
          }
        ]
      }
    ]
  },
  {
    "country": "Canada",
    "subsidiaries": [
      {
        "name": "Montreal",
        "services": [
          {
            "service": "SDWAN",
            "service_name": "PING",
            "host_name": "ca-am01"
          },
          {
            "service": "ISP1",
            "service_name": "Interface 03",
            "host_name": "ca01net-asa1",
			"sla_service_name": "ISP1_Montreal"
          },
		  {
            "service": "ISP2",
            "service_name": "Interface 04",
            "host_name": "ca01net-asa1",
			"sla_service_name": "ISP2_Montreal"
          }
        ]
      }
    ]
  },
  {
    "country": "China",
    "subsidiaries": [
      {
        "name": "Shanghai",
        "services": [
          {
            "service": "ISP1",
            "service_name": "Interface 03",
            "host_name": "cn02net-asa1"
          }
        ]
      },
      {
        "name": "Suzhou",
        "services": [
          {
            "service": "SDWAN",
            "service_name": "PING",
            "host_name": "cn-am01"
          },
          {
            "service": "ISP1",
            "service_name": "Interface 02",
            "host_name": "cn01net-asa1"
          },
           {
            "service": "ISP2",
            "service_name": "Interface 04",
            "host_name": "cn01net-asa1"
          }

        ]
      }
    ]
  },
  {
    "country": "Croatia",
    "subsidiaries": [
      {
        "name": "Zagreb",
        "services": [
          {
            "service": "MPLS",
            "service_name": "Interface 05",
            "host_name": "AMSI-ASA1"
          },
          {
            "service": "ISP1",
            "service_name": "Interface 02",
            "host_name": "si02net-asa1",
			"sla_service_name": "ISP1_Zagreb"
          }
        ]
      }
    ]
  },
  {
    "country": "France",
    "subsidiaries": [
      {
        "name": "Brest",
        "services": [
          {
            "service": "SDWAN",
            "service_name": "PING",
            "host_name": "fr-am01"
          },
          {
            "service": "ISP1",
            "service_name": "Interface 03",
            "host_name": "fr01net-ftd1"
          }
        ]
      },
      {
        "name": "Carquefou",
        "services": [
          {
            "service": "SDWAN",
            "service_name": "PING",
            "host_name": "fr-am03"
          },
          {
            "service": "ISP1",
            "service_name": "Interface 02",
            "host_name": "AMFR_ASA_Carquefou",
			"sla_service_name": "ISP1_Carquefou"
          }
        ]
      },
      {
        "name": "Cherbourg Octeville",
        "services": [
          {
            "service": "SDWAN",
            "service_name": "PING",
            "host_name": "fr-am14"
          },
          {
            "service": "ISP1",
            "service_name": "Interface 02",
            "host_name": "fr14net-ftd1",
			"sla_service_name": "ISP1_Cherbourg"
          },
		  {
            "service": "ISP2",
            "service_name": "Interface 05",
            "host_name": "fr14net-ftd1",
			"sla_service_name": "ISP2_Cherbourg"
          }
        ]
      },
      {
        "name": "Cherbourg La Hague",
        "services": [
          {
            "service": "SDWAN",
            "service_name": "PING",
            "host_name": "fr-am09"
          },
		  {
            "service": "ISP1",
            "service_name": "Interface 02",
            "host_name": "fr01net-ftd1",
			"sla_service_name": "ISP1_LaHague"
          }
        ]
      },
      {
        "name": "Cherbourg La Saline",
        "services": [
          {
            "service": "SDWAN",
            "service_name": "PING",
            "host_name": "fr-am08"
          },
          {
            "service": "ISP1",
            "service_name": "Interface 04",
            "host_name": "fr08net-ftd1",
	    "sla_service_name": "ISP1_LaSaline"
          }

        ]
      },
      {
        "name": "Lanester",
        "services": [
          {
            "service": "SDWAN",
            "service_name": "PING",
            "host_name": "fr-am02"
          },
          {
            "service": "ISP1",
            "service_name": "Interface 02",
            "host_name": "fr02net-ftd1",
            "sla_service_name": "ISP1_Lanester"
          },
          {
            "service": "ISP2",
            "service_name": "Interface 05",
            "host_name": "fr02net-ftd1",
            "sla_service_name": "ISP2_Lanester"
          }

        ]
      },
      {
        "name": "L Ardoise",
        "services": [
          {
            "service": "SDWAN",
            "service_name": "PING",
            "host_name": "fr-am06"
          },
          {
            "service": "ISP1",
            "service_name": "Interface 02",
            "host_name": "fr06net-asa1"
          }
        ]
      },
      {
        "name": "Lyon",
        "services": [
          {
            "service": "SDWAN",
            "service_name": "PING",
            "host_name": "fr-am16"
          },
		  {
            "service": "ISP1",
            "service_name": "Interface 02",
            "host_name": "fr16net-ftd1",
			"sla_service_name": "ISP1_Lyon"
          }
        ]
      },
      {
        "name": "Montigny",
        "services": [
          {
            "service": "MPLS",
            "service_name": "Interface 1",
            "host_name": "Montigny-Full"
          },
          {
            "service": "ISP1",
            "service_name": "Interface 02",
            "host_name": "fr20net-asa1"
          },
          {
            "service": "MPLS SG",
            "service_name": "Interface 05",
            "host_name": "Montigny-SG"
          }
        ]
      },
      {
        "name": "Toulouse",
        "services": [
          {
            "service": "SDWAN",
            "service_name": "PING",
            "host_name": "fr-am04"
          },
          {
            "service": "ISP1",
            "service_name": "Interface 02",
            "host_name": "fr04net-asa1"
          }
        ]
      }
    ]
  },
  {
    "country": "Germany",
    "subsidiaries": [
      {
        "name": "Augsburg",
        "services": [
          {
            "service": "ISP1",
            "service_name": "Interface 02",
            "host_name": "de03net-asa1"
          },
          {
            "service": "ISP2",
            "service_name": "Interface 04",
            "host_name": "de03net-asa1"
          }
        ]
      },
      {
        "name": "Berlin",
        "services": [
          {
            "service": "SDWAN",
            "service_name": "PING",
            "host_name": "de-am01"
          },
          {
            "service": "ISP1",
            "service_name": "Interface 5",
            "host_name": "euDE_ASA01",
			"sla_service_name": "ISP1_Berlin"
          },
          {
            "service": "MPLS SG",
            "service_name": "Interface 01",
            "host_name": "Germany-SG"
          }
        ]
      },
      {
        "name": "Düsseldorf",
        "services": [
          {
            "service": "ISP1",
            "service_name": "Interface 02",
            "host_name": "de02net-asa1",
			"sla_service_name": "ISP1_Dusseldorf"
          }
        ]
      }
    ]
  },
  {
    "country": "Hungary",
    "subsidiaries": [
      {
        "name": "Budapest",
        "services": [
          {
            "service": "SDWAN",
            "service_name": "PING",
            "host_name": "hu-am01"
          },

          {
            "service": "ISP1",
            "service_name": "Interface 02",
            "host_name": "hu01net-asa1",
			"sla_service_name": "ISP1_Hungary"
          },
          {
            "service": "ISP2",
            "service_name": "Interface 04",
            "host_name": "hu01net-asa1",
                        "sla_service_name": "ISP2_Hungary"
          }
        ]
      }
    ]
  },
  {
    "country": "India",
    "subsidiaries": [
      {
        "name": "Chennai",
        "services": [
          {
            "service": "ISP1",
            "service_name": "Interface 02",
            "host_name": "in01net-asa1",
			"sla_service_name": "ISP1_India"
          },
          {
            "service": "ISP2",
            "service_name": "Interface 05",
            "host_name": "in01net-asa1",
			"sla_service_name": "ISP2_India"
          }
        ]
      }
    ]
  },
  {
    "country": "Latvia",
    "subsidiaries": [
      {
        "name": "Riga",
        "services": [
          {
            "service": "MPLS",
            "service_name": "Interface 01",
            "host_name": "Latvia--HSRP"
          },
          {
            "service": "ISP1",
            "service_name": "Interface 02",
            "host_name": "lv01net-asa1",
			"sla_service_name": "ISP1_Latvia"
          },
          {
            "service": "MPLS SG",
            "service_name": "Interface 01",
            "host_name": "Latvia-SG"
          }
        ]
      }
    ]
  },
  {
    "country": "Luxembourg",
    "subsidiaries": [
      {
        "name": "Bertrange",
        "services": [
		  {
            "service": "SDWAN",
            "service_name": "PING",
            "host_name": "lu-am01"
          },
          {
            "service": "MPLS",
            "service_name": "Interface GigabitEthernet0/0/0",
            "host_name": "Luxembourg-HSRP"
          },
          {
            "service": "ISP1",
            "service_name": "Interface 272",
            "host_name": "lu01net-f5",
			"sla_service_name": "ISP1_Luxembourg"
          },
          {
            "service": "ISP2",
            "service_name": "Interface 288",
            "host_name": "lu01net-f5",
			"sla_service_name": "ISP2_Luxembourg"
          },
          {
            "service": "MPLS SG",
            "service_name": "Interface GigabitEthernet0/0",
            "host_name": "Luxembourg-SG"
          }
        ]
      }
    ]
  },
  {
    "country": "Poland",
    "subsidiaries": [
      {
        "name": "Krakow",
        "services": [
          {
            "service": "SDWAN",
            "service_name": "PING",
            "host_name": "pl-am01"
          },
          {
            "service": "ISP1",
            "service_name": "Interface 02",
            "host_name": "pl01net-ftd2",
                    "sla_service_name": "ISP1_Poland"
          },
          {
            "service": "ISP2",
            "service_name": "Interface 04",
            "host_name": "pl01net-ftd2",
                    "sla_service_name": "ISP2_Poland"
          },
        ]
      }
    ]
  },
  {
    "country": "Portugal",
    "subsidiaries": [
      {
        "name": "Paço de Arcos",
        "services": [
          {
            "service": "ISP1",
            "service_name": "Interface 02",
            "host_name": "pt01net-ftd1",
			"sla_service_name": "ISP1_Portugal"
          },
          {
            "service": "ISP2",
            "service_name": "Interface 04",
            "host_name": "pt01net-ftd1",
			"sla_service_name": "ISP2_Portugal"
          },
          {
            "service": "SDWAN",
            "service_name": "PING",
            "host_name": "pt-am01"
          }
        ]
      }
    ]
  },
  {
    "country": "Romania",
    "subsidiaries": [
      {
        "name": "Bucharest",
        "services": [
          {
            "service": "ISP1",
            "service_name": "Interface 02",
            "host_name": "ro01net-asa1"
          },
          {
            "service": "ISP2",
            "service_name": "Interface 04",
            "host_name": "ro01net-asa1"
          }
        ]
      },
      {
        "name": "Cluj",
        "services": [
          {
            "service": "ISP1",
            "service_name": "Interface 02",
            "host_name": "ro03net-asa1"
          },
          {
            "service": "ISP2",
            "service_name": "Interface 04",
            "host_name": "ro03net-asa1"
          }
        ]
      },
      {
        "name": "Sibiu",
        "services": [
          {
            "service": "ISP1",
            "service_name": "Interface 02",
            "host_name": "ro02net-asa1",
			"sla_service_name": "ISP1_Sibiu"
          },
          {
            "service": "ISP2",
            "service_name": "Interface 04",
            "host_name": "ro02net-asa1",
			"sla_service_name": "ISP2_Sibiu"
          },
          {
            "service": "MPLS SG",
            "service_name": "Interface FastEthernet0",
            "host_name": "Romania_SG"
          }
        ]
      }
    ]
  },
  {
    "country": "Slovenia",
    "subsidiaries": [
      {
        "name": "Novo Mesto",
        "services": [
          {
            "service": "MPLS",
            "service_name": "Interface 1",
            "host_name": "Infotehna"
          },
          {
            "service": "ISP1",
            "service_name": "Interface 03",
            "host_name": "AMSI-ASA1"
          }
        ]
      }
    ]
  },
  {
    "country": "Spain",
    "subsidiaries": [
      {
        "name": "Madrid",
        "services": [
          {
            "service": "MPLS",
            "service_name": "Interface 05",
            "host_name": "es02net-asa1"
          },
          {
            "service": "ISP1",
            "service_name": "Interface 04",
            "host_name": "es02net-asa1"
          }
        ]
      },
      {
        "name": "Vitoria",
        "services": [
          {
            "service": "MPLS",
            "service_name": "Interface 2",
            "host_name": "Spain"
          },
          {
            "service": "ISP1",
            "service_name": "Interface 03",
            "host_name": "es01net-ftd1",
			"sla_service_name": "ISP1_Vitoria"
          }
        ]
      }
    ]
  },
  {
    "country": "Switzerland",
    "subsidiaries": [
      {
        "name": "Kreuzlingen",
        "services": [
          {
            "service": "MPLS",
            "service_name": "Interface 1",
            "host_name": "Switzerland"
          },
          {
            "service": "ISP1",
            "service_name": "Interface 03",
            "host_name": "sw01net-asa1"
          },
          {
            "service": "ISP2",
            "service_name": "Interface 04",
            "host_name": "sw01net-asa1"
          }
        ]
      },
      {
        "name": "Zurich",
        "services": [
          {
            "service": "ISP1",
            "service_name": "Interface 10103",
            "host_name": "amch-sw1"
          }
        ]
      }
    ]
  },
  {
    "country": "USA",
    "subsidiaries": [
      {
        "name": "Broomfield",
        "services": [
          {
            "service": "ISP1",
            "service_name": "Interface 04",
            "host_name": "us01net-asa1"
          },
          {
            "service": "ISP2",
            "service_name": "Interface 02",
            "host_name": "us01net-asa1"
          }
        ]
      },
	  {
        "name": "River Falls",
        "services": [
          {
            "service": "ISP1",
            "service_name": "Interface 02",
            "host_name": "us02net-asa1",
			"sla_service_name": "ISP1_RiverFalls"
          },
          {
            "service": "ISP2",
            "service_name": "Interface 09",
            "host_name": "us02net-asa1",
			"sla_service_name": "ISP2_RiverFalls"
          }
        ]
      }
    ]
  }
]

@app.route("/network_info")
def get_network_info():
    c = livestatus.MultiSiteConnection(sites)
    for country in network_matrix:
        for subsidiary in country['subsidiaries']:
			for matrix_service in subsidiary['services']:
				#This marks all the services as critical in order be updated later according to the status of the interface if the machine is up
				matrix_service['state']=2
				state_updated = 0;
				if 'sla_service_name' in matrix_service:
					#this will contain the total locations used to monitor this service
					total_check = 0
					total_status_down = 0
					total_status_warning = 0
					total_status_unknown = 0
					for display_name,state in c.query("GET services\nColumns: display_name state\nFilter: display_name = " + matrix_service['sla_service_name']):
						total_check = total_check + 1

						if state == 1:
							total_status_warning = total_status_warning + 1

						if state == 2:
							total_status_down  = total_status_down + 1

						if state == 3:
							total_status_unknown = total_status_unknown + 1

					if total_check > 0 and total_status_warning > 0 and total_status_warning/total_check >= 0.5:
						matrix_service['state'] = 1
						state_updated = 1

					if total_check > 0 and total_status_down > 0 and total_status_down/total_check >= 0.5:
						matrix_service['state'] = 2
						state_updated = 1

					if total_check > 0 and total_status_unknown > 0 and total_status_unknown/total_check >= 0.5:
						matrix_service['state'] = 3
						state_updated = 1

				#this means that status was previously updated and the service is down for more than 50% locations thus don't need to check usage
				if state_updated == 1 and matrix_service['state'] == 2:
					continue

				#check usage
				for host_name,display_name,output,state in c.query("GET services\nColumns: host_name display_name plugin_output state\nFilter: host_name = " + matrix_service['host_name'] + "\nFilter: display_name = " + matrix_service['service_name']):
					if output:
						perfstring = unicode(output.strip())
						parts = perfstring.split(",")

					if parts and len(parts) > 2:
						if parts[2]:
							download_values=parts[2].split(":")
							if download_values and download_values[1]:
								matrix_service["download"]=download_values[1].lstrip()

					if parts and len(parts) > 3:
						if parts[3]:
							upload_values=parts[3].split(":")
							if upload_values and upload_values[1]:
								matrix_service["upload"]=upload_values[1].lstrip()

					if parts and parts[1]:
						matrix_service['speed']=parts[1].lstrip()

					if state_updated == 0:
						matrix_service['state'] = state
					matrix_service['output'] = output

    return jsonify(network_matrix)


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8000 ,  debug=True)
