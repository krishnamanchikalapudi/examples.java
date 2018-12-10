'use strict';

var fs = require('fs'),
    path = require('path'),
    os = require("os"),
    http = require('http');

var app = require('connect')();
var swaggerTools = require('swagger-tools');
var jsyaml = require('js-yaml');

let serverPort = 3000; // default port

// load config/default.json
/*
var contextPath = config.get("NodeServer.ContextPath");

if (config.has('NodeServer.host.port')) {
    serverPort = config.get('NodeServer.host.port');
}


// Server info
var ipAddress,
ifaces = os.networkInterfaces();
for (var dev in ifaces) {
ifaces[dev].filter((details) => details.family === 'IPv4' && details.internal === false ? ipAddress = details.address : undefined);
}
*/


// swaggerRouter configuration
var options = {
  swaggerUi: path.join(__dirname, '/swagger.json'),
  controllers: path.join(__dirname, './controllers'),
  // useStubs: process.env.NODE_ENV === 'development' // Conditionally turn on stubs (mock mode)
};

// The Swagger document (require it, build it programmatically, fetch it from a URL, ...)
var spec = fs.readFileSync(path.join(__dirname,'api/swagger.yaml'), 'utf8');
var swaggerDoc = jsyaml.safeLoad(spec);

// Initialize the Swagger middleware
swaggerTools.initializeMiddleware(swaggerDoc, function (middleware) {

  // Interpret Swagger resources and attach metadata to request - must be first in swagger-tools middleware chain
  app.use(middleware.swaggerMetadata());

  // Validate Swagger requests
  app.use(middleware.swaggerValidator());

  // Route validated requests to appropriate controller
  app.use(middleware.swaggerRouter(options));

  // Serve the Swagger documents and Swagger UI
  app.use(middleware.swaggerUi());

  // Start the server
  http.createServer(app).listen(serverPort, function () {
	 console.log('API server started...'); 
	 // const apiUri = ("http://" + ipAddress + ":" + serverPort + contextPath );
     // console.log('Swagger-ui is available on %s/docs)', apiUrl);
  });

});
