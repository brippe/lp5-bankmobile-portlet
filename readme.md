# BankMobile Student Refund Disbursement Signin Portlet for Luminis 5

This is a simple Luminis 5.3 portlet for logging into BankMobile refund disbursements. 

## Configuring the portlet
To configure the portlet, you will need change your two properties in the `webapp/META-INF/luminis-applicationContext.xml`
for the `edu.nocccd.portlets.lp.config.AppConfig` bean. Change the constructor-arg for the 

1. `clientCode` to your BankMobile provided client code.
2. `sharedKey` to  your BankMobile provided sharedKey.
3. `refundURL` the refund url for BankMobile (this url should only be configured if BankMobile changes their refund url)

## Build and Package
Before you begin, you will need to update the `pom.xml` to point to your local luminis directory for it's dependencies on
Luminis. You need to update that `<luminis.dir>` to be the luminis home directory or your $CP_ROOT directory. Once that's
complete, you can package the application my running:

`mvn clean package`

This will create a war file that you can deploy in your `$CP_ROOT/products/liferay/liferay-admin|liferay-portal/deploy`
directory to deploy the portlet.

## Deploy without building 
You can use the war from the releases, then you need to edit the `luminis-applicationContext.xml` file mentioned above,
changing the properties above. You will need to update the war file before deploying the war file. Next, copy the updated
war file to your admin and portal tiers in the `$CP_ROOT/products/liferay/liferay-admin|liferay-portal/deploy`
directory. Finally, you should be able to add the portlet to your site's layout.  

###### The portlet has been tested on Luminis 5.3.0.107.

