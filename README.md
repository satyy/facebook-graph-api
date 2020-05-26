# facebook-graph-api
- Working with Facebook Graph Api. 
- Sample application to publish current covid19 stats for configured places(India) to Facebook Group.
- This application expose REST API's to get the current covid19 stats of INDIA as well as Publish stats for few configured places to facebook group.
- Also, there is a scheduler (with cron expression configuration) which periodically runs and publish stats to facebook group.

## Pre-requisite:
1. Java - 11
2. Gradle

## API's exposed

<pre>
1. <b>GET</b>      /covid/tracker/v1/inida                  - get detail information about current covid19 stats for all the states and districts in India.
2. <b>POST</b>     /covid/fb/v1/group                       - publish the information to configured facebook group.
</pre>

## Configuration
- The app configuration is externalized and currently added to `application.properties` present in resource path.
- To publish stats to facebook group, below two configuration should be configured
   1. fb.api.group.nodeId - The facebook group node id for the group to which has data has to be published.
   2. fb.api.group.accessToken - Access token with permission `publish_to_groups`. The user should have admin priviledge in order to pu blish to group.

- Next, you can also configure the intrested districts in India for which the stats will be published. 
   1. `fb.post.district.names`, this can take multiple district names all separated by comma.

## Build and Run
1. Checkout repo.
2. run cmd `sh run-app.sh`

## Port Used 
The appication is configured to run on port **8888** which can be changed by modifying **server.port** in application.properties 
