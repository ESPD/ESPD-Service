package eu.europa.ec.grow.espd.util;

/**
 * <p>
 * Represents a function that accepts one argument and produces a result.
 * </p>
 * <p>This is a functional interface whose functional method is {@link #apply(T)}.
 * </p>
 *
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 *            <p/>
 *            Created by vigi on 11/11/15:10:54 AM.
 */
public interface Function<T, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     *
     * @return the function result
     */
    R apply(T t);
}
