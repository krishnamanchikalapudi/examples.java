'use strict';


/**
 * get person details by id
 *
 * personId Long ID of person to return
 * returns Person
 **/
exports.getPersonById = function(personId) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {"id": personId, "name": "DEF"};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * get array of person details
 *
 * returns Persons
 **/
exports.getPersons = function() {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = [ { "id": 100, "name": "pqr" }, { "id": 200, "name": "mno" } ];
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}

