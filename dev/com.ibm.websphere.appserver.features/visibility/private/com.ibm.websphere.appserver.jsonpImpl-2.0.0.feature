# This private impl feature corresponds to JSON-P 2.0 with the Glassfish implementation
-include= ~${workspace}/cnf/resources/bnd/feature.props
symbolicName=com.ibm.websphere.appserver.jsonpImpl-2.0.0
singleton=true
visibility=private
-features=com.ibm.websphere.appserver.javaeeCompatible-8.0
-bundles=com.ibm.websphere.jakarta.jsonp.2.0; location:="dev/api/spec/,lib/"; mavenCoordinates="jakarta.json:jakarta.json-api:2.0.0-RC2", \
 com.ibm.ws.org.glassfish.json.1.1.jakarta
kind=noship
edition=core
WLP-Activation-Type: parallel
