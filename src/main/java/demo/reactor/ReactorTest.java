package demo.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;

public class ReactorTest {

    public static void main(String[] args) {
//        Flux.just("Hello", "World").doOnNext(Consumer).subscribe(System.out::println);

//        Flux.fromArray(new Integer[]{1,2,3}).then().subscribe(System.out::println);
//
//        Flux.empty().then().subscribe(System.out::println);
//
//        Mono.just("Hello").then().subscribe(x -> System.out.println(x));


        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        Mono.just("Hello").subscribe(consumer);

        String[] a = new String[]{"4","2","6","1"};
        Arrays.sort(a, String::indexOf);

        Flux.fromArray(a).subscribe(System.out::println);
        Mono.create(monoSink -> monoSink.success(111L));
    }
}
