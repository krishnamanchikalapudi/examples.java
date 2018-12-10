'use strict';


/**
 * gets the status of the microservice itself.
 * gets the status of the entire given system, including all downstream systems. in this case,it would represent the status of the deposit prospect api  microservice as well as the qualifile source system.
 *
 * returns Object
 **/
exports.liveStatusGET = function() {
  return new Promise(function(resolve, reject) {
    var examples = {};
    var datetime = new Date();
    examples['application/json'] = {
  "live" : true,
  "liveAsOf" : datetime,
  "buildDate" : "2018-01-07t15:12:22z",
  "artifactVersion" : "snapshot-123456"
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * gets the status of the microservice backend systems.
 * gets the status of the entire given system, including all downstream systems. in this case,it would represent the status of the deposit prospect api  microservice as well as the qualifile source system.
 *
 * returns Object
 **/
exports.readyStatusGET = function() {
  return new Promise(function(resolve, reject) {
    var examples = {};
    var datetime = new Date();
    examples['application/json'] = {
  "ready" : true,
  "readyAsOf" : datetime,
  "backends" : [ {
    "name" : "no backend",
    "status" : 200
  } ]
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}

