package in.bigbrains.animeannotation;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes("in.bigbrains.animeannotation.Anime")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class Processor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        for( final Element element: roundEnvironment.getElementsAnnotatedWith( Anime.class ) ) {
            if (element instanceof TypeElement) {
                final TypeElement typeElement = ( TypeElement )element;

            }
        }
        return false;
    }
}
