# rudder-integration-singular-cordova

## Getting started

`$ cordova plugin add rudder-integration-singular-cordova`

## Usage
```javascript
RudderClient.initialize( <WRITE_KEY> , {
  dataPlaneUrl: <DATA_PLANE_URL> ,
  factories: [RudderSingularFactory]
})
```