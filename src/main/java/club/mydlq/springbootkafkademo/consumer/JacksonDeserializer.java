/**
 * 
 */
package club.mydlq.springbootkafkademo.consumer;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

/**
 * @author Administrator
 *
 */
public class JacksonDeserializer implements Deserializer<Object> {
	private ObjectMapper objectMapper;
    private String encoding = "UTF8";
    
    public JacksonDeserializer() {
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule()).registerModule(new ParameterNamesModule()).registerModule(new JaxbAnnotationModule());
	}

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        String propertyName = isKey ? "key.deserializer.encoding" : "value.deserializer.encoding";
        Object encodingValue = configs.get(propertyName);
        if (encodingValue == null)
            encodingValue = configs.get("deserializer.encoding");
        if (encodingValue instanceof String)
            encoding = (String) encodingValue;
    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        return null;
    }
}