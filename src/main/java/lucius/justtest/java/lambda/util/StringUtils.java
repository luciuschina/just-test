package lucius.justtest.java.lambda.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by Lucius on 9/10/18.
 */
public class StringUtils {
    public static String collectionToCommaDelimitedString(Collection<String> collection){
        return collection.stream().collect(Collectors.joining(","));
    }
    public static String arrayToCommaDelimitedString(String[] array){
        return collectionToCommaDelimitedString(Arrays.asList(array));
    }
}
