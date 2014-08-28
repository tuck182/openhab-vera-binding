openhab-vera-binding
=======================

openHAB Binding for the MiCasaVerda Vera Devices


### Introduction

The Vera binding is used to enable communication between openHAB and a single MiCasaVerde Vera controller. It uses the REST API to interact with the Vera.

### Installing

Extract a release zip into the addons folder of an openHAB runtime installation and restart.

### Usage

#### Configuration

The following configuration items are required to be set in openhab.cfg:

	vera:host=<local ip address of your hub>

#### Bindings

The Harmony binding supports both outgoing and incoming item bindings of the form:

    { harmonyhub="<binding>[ <binding> ...]" }
    
where `<binding>` can be:

##### TBD

#### Actions

The following actions are supported in rules:

##### TBD

### TODO

* Explanation of how to set up project for development (integrate it into an openHAB dev environment)

