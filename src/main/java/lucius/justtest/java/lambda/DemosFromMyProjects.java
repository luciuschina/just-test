package lucius.justtest.java.lambda;

import lucius.justtest.java.lambda.util.SearchHit;
import lucius.justtest.java.lambda.util.StringUtils;
import lucius.justtest.java.lambda.util.Tuple;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.spliterator;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.*;

/**
 * Created by Lucius on 9/10/18.
 */
public class DemosFromMyProjects {
    private Map<String, String> labelNameIdMap = new HashMap<>();

    public DemosFromMyProjects() {
        this.labelNameIdMap.put("1", "aaaaaa");
        this.labelNameIdMap.put("2", "b");
        this.labelNameIdMap.put("3", "cc");
    }

    public static String[] getLastNdaysArray(int n) {
        String[] days = new String[n];
        for (int i = 0; i < n; i++) {
            days[i] = "day:" + i;
        }
        return days;
    }

    public static String[] getLastNdaysArrayLambda(int n) {
        return IntStream.range(0, n).boxed().map(i -> "day:" + i).toArray(String[]::new);
    }

    //-------------------------------------------------------------

    public static List<String> getDocIdsAsList(SearchHit[] hits) {
        List<String> list = new ArrayList<>();
        for (SearchHit hit : hits) {
            list.add(hit.getId());
        }
        return list;
    }

    public static List<String> getDocIdsAsListLambda(SearchHit[] hits) {
        return stream(hits).map(x -> x.getId()).collect(Collectors.toList());
    }

    //-------------------------------------------------------------

    public static String[] getDocIdsAsArray(SearchHit[] hits) {
        String[] array = new String[hits.length];
        for (int i = 0; i < hits.length; i++) {
            array[i] = hits[i].getId();
        }
        return array;
    }

    public static String[] getDocIdsAsArrayLambda(SearchHit[] hits) {
        return stream(hits).map(x -> x.getId()).toArray(String[]::new);
    }

    //--------------------------------------------------

    public static Set<String> arrayToSet(String sentinelNodes) {
        String[] nodes = sentinelNodes.split(",");
        Set<String> setNodes = new HashSet<String>();
        for (String n : nodes) {
            setNodes.add(n.trim());
        }
        return setNodes;
    }

    public static Set<String> arrayToSetLambda(String sentinelNodes) {
        return stream(sentinelNodes.split(",")).map(x -> x.trim()).collect(Collectors.toSet());
    }

    //--------------------------------------------------

    public String getLabelIdsByNames(String labelNames) {
        List<String> list = new ArrayList<>();
        for (String labelName : labelNames.split(",")) {
            if (this.labelNameIdMap.containsKey(labelName)) {
                list.add(this.labelNameIdMap.get(labelName));
            }
        }
        return StringUtils.collectionToCommaDelimitedString(list);
    }

    public String getLabelIdsByNamesLambda(String labelNames) {
        return stream(labelNames.split(","))
                .map(labelName -> this.labelNameIdMap.getOrDefault(labelName, null))
                .filter(x -> x != null)
                .collect(Collectors.joining(","));
    }

    //----------------------------------

    public Map<String, String[]> initChannelMapLambda(List<Tuple<String, String>> list) {
        Map<String, String[]> channelMap = new HashMap<>();
        list.stream().collect(groupingBy(Tuple::getT1, mapping(Tuple::getT2, toList())))
                .forEach((k, v) -> channelMap.put(k, v.toArray(new String[]{})));
        return channelMap;
    }

    //----------------------------------
    public String findFirstElement() {
        String result = "";
        for (Map.Entry<String, String> entry : labelNameIdMap.entrySet()) {
            if (entry.getValue().length() < 3) {
                result = entry.getKey();
                break;
            }
        }
        return result;
    }

    public String findFirstElementLambda() {
        return labelNameIdMap.entrySet().stream()
                .filter(x -> x.getValue().length() < 3)
                .map(Map.Entry::getKey)
                .findFirst().orElse("");
    }

    //------------------------------

    private List<Double> findDoubles(String text) {
        List<Double> result = new ArrayList<>(2);
        for (String x : text.split("[^\\d\\.]")) {
            if (!"".equals(x)) {
                result.add(Double.parseDouble(x));
            }
        }
        return result;


    }

    private List<Double> findDoublesLambda(String text) {
        return stream(text.split("[^\\d\\.]"))
                .filter(x -> !"".equals(x))
                .mapToDouble(x -> Double.parseDouble(x))
                .boxed().collect(toList());
    }


    public static void main(String[] args) {
        DemosFromMyProjects d = new DemosFromMyProjects();
        System.out.println(arrayToSet("1,2,3"));
        System.out.println(arrayToSetLambda("1,2,3"));

        String labelNames = "1,2,3";
        System.out.println(d.getLabelIdsByNames(labelNames));
        System.out.println(d.getLabelIdsByNamesLambda(labelNames));

        List<Tuple<String, String>> list = new ArrayList<>();
        list.add(new Tuple<>("1", "ab"));
        list.add(new Tuple<>("1", "ab2"));
        list.add(new Tuple<>("2", "dd"));
        list.add(new Tuple<>("2", "dd"));
        list.add(new Tuple<>("3", "xadfas"));


        System.out.println(d.findDoublesLambda("12.3~~22.3"));
    }
}
