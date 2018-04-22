package neo.client;

import tarchia.greeting.SayHello;

import static javax.xml.XMLConstants.XML_NS_PREFIX;
public class HelloModules {
    public static void main (String [] args) {
        System.out.println(
                "Hi there!"
        );
        System.out.println("The XML namespace prefix is: " + XML_NS_PREFIX);

        SayHello greeter = new SayHello();
        System.out.println(greeter.hi() );

    }
}
