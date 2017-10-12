package gr.cite.earthserver.xwcpsmars.application;

import gr.cite.earthserver.xwcpsmars.mars.MarsRequest;
import gr.cite.earthserver.xwcpsmars.rasdaman.CoverageRegistryRasdamanConnector;
import gr.cite.earthserver.xwcpsmars.rasdaman.RasdamanClient;
import gr.cite.earthserver.xwcpsmars.rasdaman.RasdamanException;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistry;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistryException;
import gr.cite.earthserver.xwcpsmars.utils.WCSRequestParameters;
import gr.cite.femme.client.FemmeClient;
import gr.cite.femme.client.FemmeException;
import gr.cite.femme.client.api.FemmeClientAPI;
import gr.cite.femme.core.model.DataElement;
import gr.cite.femme.core.model.Metadatum;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.util.Collections;

public class XwcpsMarsTest {

	private CoverageRegistryRasdamanConnector coverageRegistryRasdamanConnector;
	private RasdamanClient rasdamanClient;
	private FemmeClientAPI femmeClient;
	private CoverageRegistry coverageRegistry;

//	@Before
	public void init() throws FemmeException, CoverageRegistryException, RasdamanException, IOException {
		this.rasdamanClient = new RasdamanClient(
				"http://earthserver.ecmwf.int/rasdaman/ows",
				"python /home/kapostolopoulos/Desktop/xwcps-mars/rasdaman/wcst_import.py",
				"ingredients_template.json",
				"/home/kapostolopoulos/Desktop/xwcps-mars/rasdaman/ingredients",
				"/home/kapostolopoulos/Desktop/xwcps-mars/rasdaman/registration",
				"/home/kapostolopoulos/Desktop/xwcps-mars/rasdaman/responses",
				true
				);
		this.femmeClient = new FemmeClient("http://localhost:8090/femme-application-devel");
		this.coverageRegistry = new CoverageRegistry(this.femmeClient);

		this.coverageRegistryRasdamanConnector = new CoverageRegistryRasdamanConnector(this.rasdamanClient, this.coverageRegistry);
		System.out.println("MARS collection id: " + this.coverageRegistryRasdamanConnector.getMarsCollectionId());
	}

	//@Test
	public void register() throws FemmeException {
		String registrationMetadata = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<gmlcov:ReferenceableGridCoverage\n" +
				"        xmlns='http://www.opengis.net/gml/3.2'\n" +
				"        xmlns:gml='http://www.opengis.net/gml/3.2'\n" +
				"        xmlns:gmlcov='http://www.opengis.net/gmlcov/1.0'\n" +
				"        xmlns:swe='http://www.opengis.net/swe/2.0'\n" +
				"        xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'\n" +
				"        xmlns:gmlrgrid='http://www.opengis.net/gml/3.3/rgrid'\n" +
				"        gml:id=\"sst_test\"\n" +
				"        >\n" +
				"\n" +
				"<gmlcov:metadata>\n" +
				"{\n" +
				"  \"MARS type\": \"an\", \n" +
				"  \"Grib1_Parameter_id\": \"34\", \n" +
				"  \"Originating_or_generating_Center\": \"European Centre for Medium-Range Weather Forecasts\", \n" +
				"  \"slices\": [], \n" +
				"  \"GRIB_table_version\": \"34.128\", \n" +
				"  \"Type of level\": \"surface\", \n" +
				"  \"Grib1_Parameter_name\": \"Sea surface temperature\"\n" +
				"}\n" +
				"</gmlcov:metadata>\n" +
				"\n" +
				"<boundedBy>\n" +
				"    <Envelope srsName=\"http://earthserver2d.ecmwf.int:8080/def/crs-compound?1=http://earthserver2d" +
				".ecmwf.int:8080/def/crs/EPSG/0/4326&amp;2=http://earthserver2d.ecmwf" +
				".int:8080/def/crs/OGC/0/AnsiDate\" axisLabels=\"Lat Long ansi\" uomLabels=\"  http://www.opengis" +
				".net/def/uom/UCUM/0/d\" srsDimension=\"3\">\n" +
				"        <lowerCorner>-10.25 -10.25 \"2003-04-12T18:00:00+00:00\"</lowerCorner>\n" +
				"        <upperCorner>10.25 10.25 \"2003-04-12T18:00:00+00:00\"</upperCorner>\n" +
				"    </Envelope>\n" +
				"</boundedBy>\n" +
				"\n" +
				"\n" +
				"<domainSet>\n" +
				"    <gml:ReferenceableGridByVectors dimension=\"3\" gml:id=\"grid\">\n" +
				"        <limits>\n" +
				"            <GridEnvelope>\n" +
				"                <low>0 0 0</low>\n" +
				"                <high>0 40 40</high>\n" +
				"            </GridEnvelope>\n" +
				"        </limits>\n" +
				"        <axisLabels>ansi Long Lat</axisLabels>\n" +
				"        <gml:origin>\n" +
				"            <Point gml:id=\"origin\" srsName=\"http://earthserver2d.ecmwf" +
				".int:8080/def/crs-compound?1=http://earthserver2d.ecmwf.int:8080/def/crs/EPSG/0/4326&amp;" +
				"2=http://earthserver2d.ecmwf.int:8080/def/crs/OGC/0/AnsiDate\"\n" +
				"                   axisLabels=\"Lat Long ansi\" uomLabels=\"  http://www.opengis" +
				".net/def/uom/UCUM/0/d\" srsDimension=\"3\">\n" +
				"                <pos>10.00 -10.00 \"2003-04-12T18:00:00+00:00\"</pos>\n" +
				"            </Point>\n" +
				"        </gml:origin>\n" +
				"\n" +
				"        <gmlrgrid:generalGridAxis>\n" +
				"            <gmlrgrid:GeneralGridAxis>\n" +
				"                <gmlrgrid:offsetVector srsName=\"http://earthserver2d.ecmwf" +
				".int:8080/def/crs-compound?1=http://earthserver2d.ecmwf.int:8080/def/crs/EPSG/0/4326&amp;" +
				"2=http://earthserver2d.ecmwf.int:8080/def/crs/OGC/0/AnsiDate\" axisLabels=\"Lat Long ansi\" " +
				"uomLabels=\"  http://www.opengis.net/def/uom/UCUM/0/d\" srsDimension=\"3\">\n" +
				"                    0 0 1\n" +
				"                </gmlrgrid:offsetVector>\n" +
				"                <gmlrgrid:coefficients>0.0</gmlrgrid:coefficients>\n" +
				"                <gmlrgrid:gridAxesSpanned>None</gmlrgrid:gridAxesSpanned>\n" +
				"                <gmlrgrid:sequenceRule axisOrder=\"+1\">None</gmlrgrid:sequenceRule>\n" +
				"            </gmlrgrid:GeneralGridAxis>\n" +
				"        </gmlrgrid:generalGridAxis>\n" +
				"         <gmlrgrid:generalGridAxis>\n" +
				"            <gmlrgrid:GeneralGridAxis>\n" +
				"                <gmlrgrid:offsetVector srsName=\"http://earthserver2d.ecmwf" +
				".int:8080/def/crs-compound?1=http://earthserver2d.ecmwf.int:8080/def/crs/EPSG/0/4326&amp;" +
				"2=http://earthserver2d.ecmwf.int:8080/def/crs/OGC/0/AnsiDate\" axisLabels=\"Lat Long ansi\" " +
				"uomLabels=\"  http://www.opengis.net/def/uom/UCUM/0/d\" srsDimension=\"3\">\n" +
				"                    0 0.5 0\n" +
				"                </gmlrgrid:offsetVector>\n" +
				"                <gmlrgrid:coefficients></gmlrgrid:coefficients>\n" +
				"                <gmlrgrid:gridAxesSpanned>None</gmlrgrid:gridAxesSpanned>\n" +
				"                <gmlrgrid:sequenceRule axisOrder=\"+1\">None</gmlrgrid:sequenceRule>\n" +
				"            </gmlrgrid:GeneralGridAxis>\n" +
				"        </gmlrgrid:generalGridAxis>\n" +
				"         <gmlrgrid:generalGridAxis>\n" +
				"            <gmlrgrid:GeneralGridAxis>\n" +
				"                <gmlrgrid:offsetVector srsName=\"http://earthserver2d.ecmwf" +
				".int:8080/def/crs-compound?1=http://earthserver2d.ecmwf.int:8080/def/crs/EPSG/0/4326&amp;" +
				"2=http://earthserver2d.ecmwf.int:8080/def/crs/OGC/0/AnsiDate\" axisLabels=\"Lat Long ansi\" " +
				"uomLabels=\"  http://www.opengis.net/def/uom/UCUM/0/d\" srsDimension=\"3\">\n" +
				"                    -0.5 0 0\n" +
				"                </gmlrgrid:offsetVector>\n" +
				"                <gmlrgrid:coefficients></gmlrgrid:coefficients>\n" +
				"                <gmlrgrid:gridAxesSpanned>None</gmlrgrid:gridAxesSpanned>\n" +
				"                <gmlrgrid:sequenceRule axisOrder=\"+1\">None</gmlrgrid:sequenceRule>\n" +
				"            </gmlrgrid:GeneralGridAxis>\n" +
				"        </gmlrgrid:generalGridAxis>\n" +
				"\n" +
				"\n" +
				"    </gml:ReferenceableGridByVectors>\n" +
				"</domainSet>\n" +
				"\n" +
				"\n" +
				"<gml:rangeSet>\n" +
				"    <gml:rangeParameters>\n" +
				"        [{\"axes\": [{\"name\": \"Lat\", \"min\": \"-10.25\", \"max\": \"10.25\", \"resolution\": " +
				"\"-0.5\", \"type\": \"number\", \"order\": \"2\"}, {\"name\": \"Long\", \"min\": \"-10.25\", \"max\":" +
				" \"10.25\", \"resolution\": \"0.5\", \"type\": \"number\", \"order\": \"1\"}, {\"name\": \"ansi\", " +
				"\"min\": \"\\\"2003-04-12T18:00:00+00:00\\\"\", \"max\": \"\\\"2003-04-12T18:00:00+00:00\\\"\", " +
				"\"resolution\": \"1\", \"type\": \"date\", \"order\": \"0\"}], \"messageId\": 1}]\n" +
				"    </gml:rangeParameters>\n" +
				"    <gml:File>\n" +
				"        <gml:fileReference><![CDATA[\n" +
				"                file:///home/deploy/data/xwcps-mars/mars/responses/2f72429d-303e-47c1-9cb9" +
				"-d336c6be1a63\n" +
				"            ]]></gml:fileReference>\n" +
				"        <gml:fileStructure>application/grib</gml:fileStructure>\n" +
				"    </gml:File>\n" +
				"</gml:rangeSet>\n" +
				"\n" +
				"\n" +
				"<gmlcov:rangeType>\n" +
				"    <swe:DataRecord>\n" +
				"        <swe:field name=\"2_metre_temperature_surface\">\n" +
				"            <swe:Quantity definition=\"\">\n" +
				"                <swe:description>14</swe:description>\n" +
				"                <swe:nilValues>\n" +
				"                    <swe:NilValues>\n" +
				"                    <swe:nilValue reason=\"Nil value represents missing values.\">\n" +
				"                        9999\n" +
				"                    </swe:nilValue>\n" +
				"                    </swe:NilValues>\n" +
				"                </swe:nilValues>\n" +
				"                <swe:uom code=\"\"/>\n" +
				"            </swe:Quantity>\n" +
				"        </swe:field>\n" +
				"\n" +
				"    </swe:DataRecord>\n" +
				"</gmlcov:rangeType>\n" +
				"\n" +
				"\n" +
				"</gmlcov:ReferenceableGridCoverage>";

		// TODO Insert in Femme
		DataElement marsRegistration = new DataElement();
		marsRegistration.setName("sst_test");
		marsRegistration.setEndpoint("http://earthserver.ecmwf.int/rasdaman/ows");

		Metadatum marsRegistrationMetadatum = new Metadatum();
		marsRegistrationMetadatum.setValue(registrationMetadata);
		marsRegistrationMetadatum.setName("MARS coverage " + marsRegistration.getName() + " Rasdaman registration metadata");
		marsRegistrationMetadatum.setContentType(MediaType.APPLICATION_XML);

		marsRegistration.setMetadata(Collections.singletonList(marsRegistrationMetadatum));

		String marsCoverageId = this.femmeClient.addToCollection(marsRegistration, this.coverageRegistryRasdamanConnector.getMarsCollectionId());
		System.out.println(marsCoverageId);
	}

//	@Test
	public void requestFromFemme() throws CoverageRegistryException {
		MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
		map.add("service", "WCS");
		map.add("version", "2.0.1");
		map.add("request", "GetCoverage");
		map.add("coverageId", "sst_test");
		map.add("subset", "Lat(-10,10)");
		map.add("subset", "Long(-10,10)");
		map.add("subset", "ansi(\"2003-04-12T18:00\")");

		WCSRequestParameters wcsRequestParameters = new WCSRequestParameters(map, this.coverageRegistryRasdamanConnector.getCoverageRegistry());
		MarsRequest marsRequest = wcsRequestParameters.buildMarsRequest();
		System.out.println(marsRequest);
	}
}
