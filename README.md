MAVEN JEE EJB 3.1 template project
=====================================
Template EJB 3.1 project for JBOSS 7.1 


MAVEN PLUGINS IN USE
====================

PMD Plugin
----------

* pmd:pmd creates a PMD site report based on the rulesets and configuration set in the plugin. It can also generate a pmd output file aside from the site report in any of the following formats: xml, csv or txt.
* pmd:cpd generates a report for PMD's Copy/Paste Detector (CPD) tool. It can also generate a cpd results file in any of these formats: xml, csv or txt.
* pmd:check verifies that the PMD report is empty and fails the build if it is not. This goal is executed by default when pmd:pmd is executed.
* pmd:cpd-check verifies that the CPD report is empty and fails the build if it is not. This goal is executed by default when pmd:cpd is executed.

http://maven.apache.org/plugins/maven-pmd-plugin/

Failsafe Plugin
---------------

* failsafe:integration-test runs the integration tests of an application.
* failsafe:verify verifies that the integration tests of an application passed.

http://maven.apache.org/plugins/maven-failsafe-plugin/

Cobertura Plugin
----------------

* cobertura:check Check the Last Instrumentation Results.
* cobertura:clean Clean up rogue files that cobertura maven plugin is tracking.
* cobertura:dump-datafile Cobertura Datafile Dump Mojo.
* cobertura:instrument Instrument the compiled classes.
* cobertura:cobertura Instruments, Tests, and Generates a Cobertura Report.

http://mojo.codehaus.org/cobertura-maven-plugin/

JbossAS Plugin
--------------

* jboss-as:deploy Deploys the application.
* jboss-as:redeploy Redeploys the application.
* jboss-as:undeploy Undeploys the application.

https://github.com/jbossas/jboss-as-maven-plugin
