package com.authorizationserver.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.*;

public class CommonUtil {
    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);
    private static final String localIp = null;

    //    private static ModelMapper mapper = null;
    public CommonUtil() {
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static <T> String beanToString(T value) {
        if (value == null) {
            return null;
        } else {
            Class<?> clazz = value.getClass();
            if (clazz != Integer.TYPE && clazz != Integer.class) {
                if (clazz == String.class) {
                    return (String) value;
                } else if (clazz != Long.TYPE && clazz != Long.class) {
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.registerModule(new JavaTimeModule());
                    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
                    String jsonString = "";
                    try {
                        jsonString = mapper.writeValueAsString(value);
                    } catch (JsonProcessingException var5) {
                        jsonString = "Can't build json from object";
                    }
                    return jsonString;
                } else {
                    return "" + value;
                }
            } else {
                return "" + value;
            }
        }
    }

    public static <T> String listToJson(List<T> list) {
        if (list != null && list.size() != 0) {
            String json = "[";
            Object item;
            for (Iterator var2 = list.iterator(); var2.hasNext(); json = json + beanToString(item) + ",") {
                item = var2.next();
            }
            String var10000 = json.substring(0, json.length() - 1);
            json = var10000 + "]";
            return json;
        } else {
            return null;
        }
    }

    public static <T> List<T> jsonToList(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
        List<T> ts = mapper.readValue(json, listType);
        return ts;
    }

    public static <T> T stringToBean(String str, Class<T> clazz) {
        if (str != null && str.length() > 0 && clazz != null) {
            if (clazz != Integer.TYPE && clazz != Integer.class) {
                if (clazz == String.class) {
                    return (T) str;
                } else if (clazz != Long.TYPE && clazz != Long.class) {
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
                    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
                    try {
                        return mapper.readValue(str, clazz);
                    } catch (IOException var4) {
                        IOException e = var4;
                        logger.error("stringToBean Error " + e.getMessage(), e);
                        return null;
                    }
                } else {
                    return (T) Long.valueOf(str);
                }
            } else {
                return (T) Integer.valueOf(str);
            }
        } else {
            return null;
        }
    }

    public static String getLocalIp() {
        if (!isNullOrEmpty(localIp)) {
            return localIp;
        } else {
            InetAddress result = null;
            try {
                int lowest = Integer.MAX_VALUE;
                Enumeration<NetworkInterface> nics = NetworkInterface.getNetworkInterfaces();
                label56:
                while (true) {
                    NetworkInterface ifc;
                    while (true) {
                        do {
                            if (!nics.hasMoreElements()) {
                                break label56;
                            }
                            ifc = nics.nextElement();
                        } while (!ifc.isUp());
                        if (ifc.getIndex() >= lowest && result != null) {
                            if (result != null) {
                                continue;
                            }
                            break;
                        }
                        lowest = ifc.getIndex();
                        break;
                    }
                    Enumeration<InetAddress> addrs = ifc.getInetAddresses();
                    while (addrs.hasMoreElements()) {
                        InetAddress address = addrs.nextElement();
                        if (address instanceof Inet4Address && !address.isLoopbackAddress()) {
                            result = address;
                        }
                    }
                }
            } catch (IOException var7) {
            }
            if (result != null) {
                return result.getHostAddress();
            } else {
                try {
                    return InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException var6) {
                    return null;
                }
            }
        }
    }

    public static String randomString(int length) {
        int leftLimit = 48;
        int rightLimit = 122;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1).filter((i) -> {
            return (i <= 57 || i >= 65) && (i <= 90 || i >= 97);
        }).limit(length).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        return generatedString.toUpperCase();
    }

    public static Long safeToLong(Object obj) {
        return safeToLong(obj, null);
    }

    public static Long safeToLong(Object obj, Long defaultValue) {
        return obj != null ? Long.parseLong(obj.toString()) : defaultValue;
    }

    public static String safeToString(Object obj, String defaultValue) {
        return obj != null && obj != "" ? obj.toString() : defaultValue;
    }

    public static String safeToString(Object obj) {
        return safeToString(obj, null);
    }

    public static Double safeToDouble(Object obj, Double defaultValue) {
        return obj != null ? Double.parseDouble(obj.toString()) : defaultValue;
    }

    public static boolean listIsEmptyOrNull(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> T convertStringToObject(String str, TypeReference<T> typeReference) {
        if (!isNullOrEmpty(str) && typeReference != null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            try {
                return typeReference.getType().equals(String.class) ? (T) str : mapper.readValue(str, typeReference);
            } catch (IOException var4) {
                IOException e = var4;
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static List<String> convertJsonToListString(String json) {
        List<String> results = new ArrayList();
        String item = json;
        if (!isNullOrEmpty(json)) {
            item = item.replace("]", "");
            item = item.replace("[", "");
            String[] list = item.split(",");
            String[] var4 = list;
            int var5 = list.length;
            for (int var6 = 0; var6 < var5; ++var6) {
                String s = var4[var6];
                if (!isNullOrEmpty(s)) {
                    results.add(s);
                }
            }
        }
        return results;
    }

    public static boolean compareString(String s1, String s2) {
        if (isNullOrEmpty(s1)) {
            return isNullOrEmpty(s2);
        } else {
            return s1.equals(s2);
        }
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty() || collection.stream().anyMatch(Objects::isNull);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isEmpty(Object object) {
        return object == null;
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0 || array[0] == null;
    }

    public static boolean isEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

    public static InputStream toInputStream(final String input) {
        return new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    }

    public static Date safeToDate(Object obj1) {
        return obj1 == null ? null : (Date) obj1;
    }

    public static BigDecimal safeToBigDecimal(Object obj1) {
        if (obj1 == null) {
            return BigDecimal.ZERO;
        } else {
            try {
                return new BigDecimal(obj1.toString());
            } catch (NumberFormatException var2) {
                return BigDecimal.ZERO;
            }
        }
    }

    public static <T> List<T> getResultFromListObjects(List<Object[]> listObjects, String classPath, String dateFormat) {
        try {
            List<T> result = new ArrayList();
            Iterator var4 = listObjects.iterator();
            while (var4.hasNext()) {
                Object[] objects = (Object[]) var4.next();
                Class<?> c = Class.forName(classPath);
                Constructor<?> cons = c.getConstructor();
                Object object = cons.newInstance();
                Field[] fields = object.getClass().getDeclaredFields();
                for (int i = 0; i < fields.length && i <= objects.length - 1; ++i) {
                    Field f = fields[i];
                    f.setAccessible(true);
                    Class t = f.getType();
                    Object item = objects[i];
                    if (item != null) {
                        if ("java.lang.String".equals(t.getName())) {
                            if (!(item instanceof String) && !(item instanceof Long) && !(item instanceof Character)) {
                                if (item instanceof java.sql.Date || item instanceof Date || item instanceof Timestamp) {
                                    f.set(object, DateUtils.date2StringByPattern(safeToDate(item), dateFormat));
                                }
                            } else {
                                f.set(object, safeToString(item));
                            }
                        } else if (!"java.lang.Long".equals(t.getName()) && !"long".equals(t.getName())) {
                            if (!"java.lang.Double".equals(t.getName()) && !"double".equals(t.getName())) {
                                if (!"java.lang.Boolean".equals(t.getName()) && !"boolean".equals(t.getName())) {
                                    if ("java.util.Date".equals(t.getName())) {
                                        f.set(object, safeToDate(item));
                                    } else if ("java.math.BigDecimal".equals(t.getName())) {
                                        f.set(object, safeToBigDecimal(item));
                                    }
                                } else {
                                    f.set(object, safeToString(item).equals("true"));
                                }
                            } else {
                                f.set(object, safeToDouble(item, 0.0));
                            }
                        } else {
                            f.set(object, safeToLong(item));
                        }
                    }
                }
                result.add((T) object);
            }
            return result;
        } catch (Exception var14) {
            Exception e = var14;
            e.printStackTrace();
            return new ArrayList();
        }
    }

    public static <T> T convertObjectsToClass(Object[] objects, String classPath, String dateFormat) {
        try {
            Class<?> c = Class.forName(classPath);
            Constructor<?> cons = c.getConstructor();
            Object object = cons.newInstance();
            Field[] fields = object.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length && i <= objects.length - 1; ++i) {
                Field f = fields[i];
                f.setAccessible(true);
                Class t = f.getType();
                Object item = objects[i];
                if (item != null) {
                    if ("java.lang.String".equals(t.getName())) {
                        if (!(item instanceof String) && !(item instanceof Long) && !(item instanceof Character)) {
                            if (item instanceof java.sql.Date || item instanceof Date || item instanceof Timestamp) {
                                f.set(object, DateUtils.date2StringByPattern(safeToDate(item), dateFormat));
                            }
                        } else {
                            f.set(object, safeToString(item));
                        }
                    } else if (!"java.lang.Long".equals(t.getName()) && !"long".equals(t.getName())) {
                        if (!"java.lang.Double".equals(t.getName()) && !"double".equals(t.getName())) {
                            if (!"java.lang.Boolean".equals(t.getName()) && !"boolean".equals(t.getName())) {
                                if ("java.util.Date".equals(t.getName())) {
                                    f.set(object, safeToDate(item));
                                } else if ("java.math.BigDecimal".equals(t.getName())) {
                                    f.set(object, safeToBigDecimal(item));
                                }
                            } else {
                                f.set(object, item);
                            }
                        } else {
                            f.set(object, safeToDouble(item, 0.0));
                        }
                    } else {
                        f.set(object, safeToLong(item));
                    }
                }
            }
            return (T) object;
        } catch (Exception var11) {
            Exception e = var11;
            e.printStackTrace();
            return null;
        }
    }

    public static int getRandom(int max) {
        return (int) (Math.random() * (double) max);
    }

    public static <T> T convertPcsmNullValue(T input) throws IllegalAccessException {
        Field[] fields = input.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            Field f = fields[i];
            f.setAccessible(true);
            Class<?> type = f.getType();
            Object value = f.get(input);
            if (type.isAssignableFrom(String.class) && (value == null || value == "")) {
                f.set(input, "-9");
            }
            if (type.isAssignableFrom(String[].class)) {
                String[] arrGetValue = (String[]) value;
                if (arrGetValue != null) {
                    for (int j = 0; j < arrGetValue.length; ++j) {
                        if (arrGetValue[j] == null || "".equals(arrGetValue[j])) {
                            arrGetValue[j] = "-9";
                        }
                    }
                }
                f.set(input, arrGetValue);
            }
        }
        return input;
    }

    public static Object ignoreFieldByteInObject(Object entity) {
        try {
            if (entity != null) {
                Field[] lstFiel = entity.getClass().getDeclaredFields();
                Field[] var2 = lstFiel;
                int var3 = lstFiel.length;
                for (int var4 = 0; var4 < var3; ++var4) {
                    Field field = var2[var4];
                    if (field != null) {
                        field.setAccessible(true);
                        Object value = field.get(entity);
                        if (value != null && (byte[].class == field.getType() || "byte[]".equals(field.getType().getTypeName()))) {
                            field.set(entity, null);
                        }
                    }
                }
            }
        } catch (IllegalAccessException | SecurityException | IllegalArgumentException var7) {
            Exception e = var7;
            logger.error("ignoreByteInObject - fieldName: " + e.getMessage(), e);
        }
        return entity;
    }

    public static boolean isNotNullAndEmpty(Object[] values) {
        return values != null && values.length > 0;
    }

    public static Object getValueByFieldName(Object entity, String fieldName) {
        try {
            if (entity != null) {
                Field field = entity.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(entity);
            }
            return null;
        } catch (IllegalArgumentException | IllegalAccessException | SecurityException | NoSuchFieldException e) {
            logger.error("getValueByFieldName - fieldName: {}", e.getMessage(), e);
            return null;
        }
    }

    public static Double mathRoundWithNull(Double value) {
        if (value == null) return value;
        return mathRound(value, 0);
    }

    public static double mathRound(double value) {
        return mathRound(value, null);
    }

    public static double mathRound(double value, Integer digit) {
        if (value == 0)
            return 0;
        if (digit == null)
            digit = 2;
        double numberDigit = Math.pow(10, digit);
        return Math.round(value * numberDigit) / numberDigit;
    }
}
