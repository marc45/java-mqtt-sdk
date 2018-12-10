package ir.moke.dandelion.mqtt;

@FunctionalInterface
public interface MessagePublisher<T> {

   void apply(T t) ;
}
