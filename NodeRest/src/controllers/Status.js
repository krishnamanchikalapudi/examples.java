'use strict';

var utils = require('../utils/writer.js');
var Status = require('./StatusService');

module.exports.liveStatusGET = function liveStatusGET (req, res, next) {
  Status.liveStatusGET()
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.readyStatusGET = function readyStatusGET (req, res, next) {
  Status.readyStatusGET()
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};
