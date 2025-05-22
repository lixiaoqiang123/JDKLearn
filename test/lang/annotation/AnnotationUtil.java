package lang.annotation;

import com.sun.istack.internal.Nullable;

import java.lang.annotation.Annotation;

public class AnnotationUtil {
    @Nullable
    public static <A extends Annotation> A findAnnotation(Class<?> clazz, @Nullable Class<A> annotationType) {
        if (annotationType == null) {
            return null;
        }
        return null;
    }
}
