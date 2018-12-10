'use strict';

var utils = require('../utils/writer.js');
var Person = require('./PersonService');

module.exports.getPersonById = function getPersonById (req, res, next) {
  var personId = req.swagger.params['personId'].value;
  Person.getPersonById(personId)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.getPersons = function getPersons (req, res, next) {
	Person.getPersons()
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};
