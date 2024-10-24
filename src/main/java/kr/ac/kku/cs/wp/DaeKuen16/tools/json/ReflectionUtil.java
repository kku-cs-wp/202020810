package kr.ac.kku.cs.wp.DaeKuen16.tools.json;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ReflectionUtil {

    public static <T> T createObjectFromJson(Class<T> clazz, JSONObject jsonObject) throws Exception {
        // Get the declared constructor (even if it's private) and make it accessible
        Constructor<T> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true); // Allows us to access private constructors if necessary

        // Create an instance of the class
        T obj = constructor.newInstance();

        // Get all fields of the class
        Field[] fields = clazz.getDeclaredFields();

        // Iterate over each field
        for (Field field : fields) {
            field.setAccessible(true); // Make the private fields accessible

            // Get the field name and check if the JSON object has a matching key
            String fieldName = field.getName();
            if (jsonObject.has(fieldName)) {

                // Handle lists
                if (List.class.isAssignableFrom(field.getType())) {
                    // Get the generic type of the list
                    ParameterizedType listType = (ParameterizedType) field.getGenericType();
                    Class<?> listClass = (Class<?>) listType.getActualTypeArguments()[0];

                    JSONArray jsonArray = jsonObject.getJSONArray(fieldName);
                    List<Object> list = new ArrayList<>();

                    // Convert each element in the JSONArray to the appropriate type
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Object item;
                        if (listClass.isPrimitive() || listClass == String.class || Number.class.isAssignableFrom(listClass)) {
                            item = jsonArray.get(i); // Direct assignment for primitives and strings
                        } else {
                            item = createObjectFromJson(listClass, jsonArray.getJSONObject(i)); // Recursive call for complex objects
                        }
                        list.add(item);
                    }
                    field.set(obj, list);

                // Handle primitive types and other objects
                } else {
                    Object value = jsonObject.get(fieldName);

                    // Handle primitive types (like int, boolean, etc.)
                    if (field.getType().isPrimitive()) {
                        if (field.getType() == int.class) {
                            field.setInt(obj, jsonObject.getInt(fieldName));
                        } else if (field.getType() == boolean.class) {
                            field.setBoolean(obj, jsonObject.getBoolean(fieldName));
                        }
                        // Add other primitive types if necessary
                    } else {
                        field.set(obj, value); // For objects, directly set the value
                    }
                }
            }
        }
        return obj;
    }
}
