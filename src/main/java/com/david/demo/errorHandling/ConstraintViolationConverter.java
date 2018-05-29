package com.david.demo.errorHandling;

import java.lang.annotation.Annotation;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.core.convert.converter.Converter;

import com.david.demo.validation.PasswordMatches;
import com.david.demo.validation.ValidEmail;


/**
 * Converts validation error (ConstraintViolation) into ErrorTO with appropriate codes etc.
 */
public class ConstraintViolationConverter implements Converter<ConstraintViolation<?>, ErrorTO> {

    private final Map<Class<? extends Annotation>, Converter<ConstraintViolation<?>, ErrorTO>> converters;

    /**
     * Constructor
     */
    public ConstraintViolationConverter() {
        converters = new HashMap<>();
        converters.put(NotNull.class, this::convertNotNull);
        converters.put(Pattern.class, this::convertPattern);
        converters.put(Size.class, this::convertSize);
        converters.put(Max.class, this::convertMax);
        converters.put(Min.class, this::convertMin);
        converters.put(ValidEmail.class, this::convertEmail);
        converters.put(PasswordMatches.class, this::convertPassword);
    }

    @Override
    public ErrorTO convert(ConstraintViolation<?> constraintViolation) {
        Annotation annotation = constraintViolation.getConstraintDescriptor().getAnnotation();
        Converter<ConstraintViolation<?>, ErrorTO> converter = converters.getOrDefault(annotation.annotationType(), this::defaultConverter);
        ErrorTO result = converter.convert(constraintViolation);
        return result != null ? result : defaultConverter(constraintViolation);
    }

    private ErrorTO convertNotNull(ConstraintViolation<?> constraintViolation) {
        resolveAnnotation(constraintViolation.getConstraintDescriptor().getAnnotation(), NotNull.class);
        return buildErrorTO(ErrorCodes.ERR_001_MANDATORY.getValue(), constraintViolation);
    }

    private ErrorTO convertPattern(ConstraintViolation<?> constraintViolation) {
        resolveAnnotation(constraintViolation.getConstraintDescriptor().getAnnotation(), Pattern.class);
        Object invalidValue = constraintViolation.getInvalidValue();
        ErrorTO ErrorTO = null;
        if (invalidValue instanceof CharSequence) {
            ErrorTO = buildErrorTO(ErrorCodes.ERR_002_BAD_VALUE.getValue(), constraintViolation);
        }
        return ErrorTO;
    }

    private ErrorTO convertSize(ConstraintViolation<?> constraintViolation) {
        Size annotation = resolveAnnotation(constraintViolation.getConstraintDescriptor().getAnnotation(), Size.class);
        Object invalidValue = constraintViolation.getInvalidValue();
        String propertyPath = resolvePropertyPath(constraintViolation);
        ErrorTO ErrorTO = null;
        if (invalidValue instanceof CharSequence) {
            ErrorTO = convertSizeCharSequence(annotation, (CharSequence) invalidValue, propertyPath);
        }
        if (invalidValue instanceof Collection || invalidValue instanceof Object[]) {
            int size = invalidValue instanceof Collection ? ((Collection) invalidValue).size() : ((Object[]) invalidValue).length;
            if (size < annotation.min()) {
                ErrorTO = buildErrorTO(ErrorCodes.ERR_002_BAD_VALUE.getValue(), constraintViolation);
                ErrorTO.setMessage("must be greater than or equal to " + annotation.min());
                return ErrorTO;
            } else if (size > annotation.max()) {
                ErrorTO = buildErrorTO(ErrorCodes.ERR_002_BAD_VALUE.getValue(), constraintViolation);
                ErrorTO.setMessage("must be less than or equal to " + annotation.max());
                return ErrorTO;
            }
        }
        return ErrorTO;
    }

    private ErrorTO convertSizeCharSequence(Size annotation, CharSequence invalidValue, String propertyPath) {
        if (invalidValue.length() < annotation.min()) {
            return new ErrorTO(ErrorCodes.ERR_002_BAD_VALUE.getValue(), "must be greater than or equal to " + annotation.min(), propertyPath);
        }
        return new ErrorTO(ErrorCodes.ERR_002_BAD_VALUE.getValue(), "must be less than or equal to " + annotation.max(), propertyPath);
    }

    private ErrorTO convertMax(ConstraintViolation<?> constraintViolation) {
        resolveAnnotation(constraintViolation.getConstraintDescriptor().getAnnotation(), Max.class);
        return buildErrorTO(ErrorCodes.ERR_002_BAD_VALUE.getValue(), constraintViolation);
    }

    private ErrorTO convertMin(ConstraintViolation<?> constraintViolation) {
        resolveAnnotation(constraintViolation.getConstraintDescriptor().getAnnotation(), Min.class);
        return buildErrorTO(ErrorCodes.ERR_002_BAD_VALUE.getValue(), constraintViolation);
    }

    private ErrorTO convertPassword(ConstraintViolation<?> constraintViolation) {
        return new ErrorTO(ErrorCodes.ERR_002_BAD_VALUE.getValue(), "Password doesn't match", "password");
    }

    private ErrorTO convertEmail(ConstraintViolation<?> constraintViolation) {
        return new ErrorTO(ErrorCodes.ERR_002_BAD_VALUE.getValue(), "Not a valid format", "e-mail");
    }

    private ErrorTO defaultConverter(ConstraintViolation<?> constraintViolation) {
        return buildErrorTO(ErrorCodes.ERR_002_BAD_VALUE.getValue(), constraintViolation);
    }

    private ErrorTO buildErrorTO(String errorCode, ConstraintViolation<?> constraintViolation) {
        return new ErrorTO(errorCode, constraintViolation.getMessage(), constraintViolation.getPropertyPath().toString());
    }


    private static <T extends Annotation> T resolveAnnotation(Annotation annotation, Class<T> annotationType) {
        if (!(annotation.annotationType().equals(annotationType))) {
            throw new IllegalStateException(MessageFormat.format("Wrong annotation type. Actual type is {0} but required is {1}.", annotation.getClass().getSimpleName(), annotationType.getSimpleName()));
        }
        return annotationType.cast(annotation);
    }

    private static String resolvePropertyPath(ConstraintViolation<?> constraintViolation) {
        Path propertyPath = constraintViolation.getPropertyPath();
        return propertyPath != null ? propertyPath.toString() : null;
    }
}