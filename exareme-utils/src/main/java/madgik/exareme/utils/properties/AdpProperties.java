/**
 * Copyright MaDgIK Group 2010 - 2015.
 */
package madgik.exareme.utils.properties;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.Properties;


/**
 * The art engine properties. The art.properties file is used if is in the classpath, otherwise it
 * reads the properties from the file specified in the ART_PROPERTIES environment variable.
 *
 * @author Herald Kllapi <br>
 *         University of Athens /
 *         Department of Informatics and Telecommunications.
 * @since 1.0
 */
public class AdpProperties {
	private static Logger log = LoggerFactory.getLogger(AdpProperties.class);
    private static final GenericProperties dbProperties;
    private static final GenericProperties artProperties;
    private static final GenericProperties armProperties;
    private static final GenericProperties opsProperties;
    private static final GenericProperties utilProperties;
    private static final GenericProperties envProperties;
    private static final GenericProperties cloudProperties;
    private static final GenericProperties gatewayProperties;
    private static final MutableProperties optProperties;
    private static final GenericProperties systemProperties;
    private static final GenericProperties decomposerProperties;
    private static String NEW_LINE = System.getProperty("line.separator");

    static {

        try {
            dbProperties = PropertiesFactory.loadProperties("db");
            artProperties = PropertiesFactory.loadProperties("art");
            armProperties = PropertiesFactory.loadProperties("arm");
            opsProperties = PropertiesFactory.loadProperties("builtinops");
            utilProperties = PropertiesFactory.loadProperties("util");
            envProperties = PropertiesFactory.loadProperties("env");
            cloudProperties = PropertiesFactory.loadProperties("cloud");
            optProperties = PropertiesFactory.loadMutableProperties("optimizer");
            gatewayProperties = PropertiesFactory.loadMutableProperties("gateway");
            decomposerProperties = PropertiesFactory.loadMutableProperties("decomposer");
            // load
            Properties properties = System.getProperties();
            for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
                properties.setProperty(entry.getKey(), entry.getValue());
            }
            systemProperties = new GenericProperties(properties);
            log.debug(systemProperties.getString("EXAREME_MADIS"));
           

            
        } catch (Exception e) {
            log.error("Cannot initialize properties", e);
            throw new RuntimeException("can not init props!");
        }
    }

    public AdpProperties() {
        throw new RuntimeException("Cannot create instance of this class");
    }

    public static String getNewLine() {
        return NEW_LINE;
    }

    public static GenericProperties getArtProps() {
        return artProperties;
    }

    public static GenericProperties getArmProps() {
        return armProperties;
    }

    public static GenericProperties getBuildInOpsProps() {
        return opsProperties;
    }

    public static GenericProperties getUtilProps() {
        return utilProperties;
    }

    public static GenericProperties getEnvProps() {
        return envProperties;
    }

    public static GenericProperties getCloudProps() {
        return cloudProperties;
    }

    public static MutableProperties getOptProperties() {
        return optProperties;
    }

    public static GenericProperties getDbProperties() {
        return dbProperties;
    }

    public static GenericProperties getGatewayProperties() {
        return gatewayProperties;
    }

    public static GenericProperties getSystemProperties() {
        return systemProperties;
    }

    public static GenericProperties getDecomposerProperties() {
        return decomposerProperties;
    }
}
