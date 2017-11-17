# vaadin-portlet-sample

Sample OSGi portlet based on:
  - Vaadin 8.1.6
  - Liferay 7.0.6 GA5

Docker image for Liferay 7 (if needed):
  - https://github.com/ctliv/docker-liferay-mysql/tree/7.0

Before portlet, deploy the following bundles:
  - https://repo1.maven.org/maven2/org/jsoup/jsoup/1.8.3/jsoup-1.8.3.jar
  - https://repo1.maven.org/maven2/com/vaadin/external/gentyref/1.2.0.vaadin1/gentyref-1.2.0.vaadin1.jar
  - https://repo1.maven.org/maven2/com/vaadin/vaadin-shared/8.1.6/vaadin-shared-8.1.6.jar
  - https://repo1.maven.org/maven2/com/vaadin/vaadin-server/8.1.6/vaadin-server-8.1.6.jar
  - https://repo1.maven.org/maven2/com/vaadin/vaadin-osgi-integration/8.1.6/vaadin-osgi-integration-8.1.6.jar
  - https://repo1.maven.org/maven2/com/vaadin/vaadin-client-compiled/8.1.6/vaadin-client-compiled-8.1.6.jar
  - https://repo1.maven.org/maven2/com/vaadin/vaadin-themes/8.1.6/vaadin-themes-8.1.6.jar
  - https://repo1.maven.org/maven2/com/vaadin/vaadin-liferay-integration/8.1.6/vaadin-liferay-integration-8.1.6.jar
      WARNING for Vaadin-Liferay-Integration, See:
      - https://github.com/vaadin/framework/issues/10220#issuecomment-342945718
 
