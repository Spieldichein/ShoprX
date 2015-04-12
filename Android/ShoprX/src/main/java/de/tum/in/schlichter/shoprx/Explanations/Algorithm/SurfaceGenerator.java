package de.tum.in.schlichter.shoprx.Explanations.Algorithm;


import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import de.tum.in.schlichter.shoprx.Explanations.Model.AbstractExplanation;
import de.tum.in.schlichter.shoprx.Explanations.Model.DimensionArgument;
import de.tum.in.schlichter.shoprx.Explanations.Model.Explanation;
import de.tum.in.schlichter.shoprx.Explanations.Model.LocationContext;
import de.tum.in.schlichter.shoprx.Explanations.Model.SimpleExplanation;

/**
 * Created by Nicksteal on 01.04.2015.
 */
public class SurfaceGenerator {
    private ExplanationLocalizer localizer;
    private TextFormatter formatter;

    public SurfaceGenerator(ExplanationLocalizer localizer,
                            TextFormatter formatter) {
        this.localizer = localizer;
        this.formatter = formatter;
    }

    public Explanation transform(Explanation explanation) {
        AbstractExplanation abstractExplanation= explanation.getAbstractExplanation();
        ArrayList<SimpleExplanation> simpleExplanations = renderDimensionArguments(abstractExplanation);
        if (explanation.getSimpleExplanations() != null){
            for (SimpleExplanation oneSimpleExplanation: explanation.getSimpleExplanations()){
                simpleExplanations.add(oneSimpleExplanation);
            }
        }
        if (abstractExplanation.item().isTrendy()){
            if (abstractExplanation.item().isTrendy())Log.d("trendy","trendy item!!!!!!: "+abstractExplanation.item().name());
            simpleExplanations.add(new SimpleExplanation("Suggested by our fashion experts!", SimpleExplanation.IconType.TRENDY));
        }


        /*SimpleExplanation contextArgument = renderContextArguments(abstractExplanation);

        // explanation.addPositiveReason(dimensionArguments);
        if (contextArgument!=null)
            simpleExplanations.add(contextArgument);*/
        explanation.setSimpleExplanations(simpleExplanations);


      //  explanation.simple(formatter.concat(dimensionArguments,  contextArguments));
        return explanation;
    }

    private CharSequence renderNegativeArguments(
            AbstractExplanation abstractExplanation) {
        String template = localizer.getNegativeArgumentTemplate();
        String text = String.format(template, textifyForNegativeArguments(abstractExplanation.negativeArguments()));
        CharSequence formatted = formatter.fromHtml(text);
        for (DimensionArgument argument : abstractExplanation.negativeArguments()) {
            formatted = formatter.renderClickable(formatted, argument
                    .dimension().attribute(), TextFormatting.textOf(localizer, argument.dimension().attribute().currentValue()));
        }
        return formatted;
    }

    private ArrayList<SimpleExplanation> renderDimensionArguments(
            AbstractExplanation explanation) {

        switch (explanation.category()) {
            case BY_STRONG_ARGUMENTS:
                String template = chooseRandomOne(localizer
                        .getStrongArgumentTemplates());
                ArrayList<SimpleExplanation> part1 =  render(template, explanation.primaryArguments());
                ArrayList<SimpleExplanation> supportingPart1= new ArrayList<SimpleExplanation>();
                if (explanation.hasSupportingArguments())
                    supportingPart1 = render(
                            localizer.getSupportingArgumentTemplate(),
                            explanation.supportingArguments());
                for (SimpleExplanation explanation1: supportingPart1){
                    part1.add(explanation1);
                }
                return part1;

            case BY_WEAK_ARGUMENTS:
                String templ = chooseRandomOne(localizer
                        .getWeakArgumentTemplates());
                ArrayList<SimpleExplanation> primaryPart = render(templ,
                        explanation.primaryArguments());
                ArrayList<SimpleExplanation> supportingPart= new ArrayList<SimpleExplanation>();
                if (explanation.hasSupportingArguments()){
                    if (primaryPart.size()==0){
                        supportingPart = render(
                                localizer.getSupportingArgumentTemplateSolo(),
                                explanation.supportingArguments());
                    }
                   else {
                        supportingPart = render(
                                localizer.getSupportingArgumentTemplate(),
                                explanation.supportingArguments());
                    }
                }

                for (SimpleExplanation explanation1: supportingPart){
                    primaryPart.add(explanation1);
                }
                return primaryPart;
                //return formatter.concat(primaryPart, supportingPart);

            case BY_SERENDIPITOUSITY:
                ArrayList<SimpleExplanation> list = new ArrayList<SimpleExplanation>();
                list.add(new SimpleExplanation(chooseRandomOne(localizer.getSerendipitousityTemplates()), SimpleExplanation.IconType.RANDOM));
                return list;
              //  return chooseRandomOne(localizer.getSerendipitousityTemplates());
            case BY_GOOD_AVERAGE:
                ArrayList<SimpleExplanation> list1 = new ArrayList<SimpleExplanation>();
                list1.add(new SimpleExplanation(localizer.getGoodAverageTemplate(), SimpleExplanation.IconType.RANDOM));
                return list1;
               // return localizer.getGoodAverageTemplate();
            case BY_LAST_CRITIQUE:
                ArrayList<SimpleExplanation> list2 = new ArrayList<SimpleExplanation>();
                list2.add(new SimpleExplanation(localizer.getLastCritiqueTemplate(), SimpleExplanation.IconType.LAST_CRITIQUE));
                return list2;
               // return localizer.getLastCritiqueTemplate();
            default:
                Log.e("NEVER ALLOWED","no category so no explanations");
                return new ArrayList<SimpleExplanation>();
        }
    }

    private SimpleExplanation renderContextArguments(AbstractExplanation explanation) {
        if (explanation.hasContextArguments()) {
            String template = chooseRandomOne(localizer
                    .getContextArgumentTemplates());
            LocationContext locContext = (LocationContext) explanation
                    .contextArguments().iterator().next().context();
            String exp = String.format(template,
                    locContext.distanceToUserInMeters(explanation.item()));
            return new SimpleExplanation(exp, SimpleExplanation.IconType.LOCATION);
           // return formatter.fromHtml(exp);
        } else
            return null;
    }

    private ArrayList<SimpleExplanation> render(String template,
                                Collection<DimensionArgument> arguments) {
        ArrayList<SimpleExplanation>textified = textify(arguments);
        ArrayList<SimpleExplanation>result = new ArrayList<SimpleExplanation>();

        for (SimpleExplanation oneExplanation: textified){

            oneExplanation.setText(String.format(template, oneExplanation.getText()));
            result.add(oneExplanation);
            Log.d("ExplanationTag search","oneExplanation in render:"+oneExplanation.getText());
        }
        return result;

        /*String text = String.format(template, textify(arguments));
        CharSequence formatted = formatter.fromHtml(text);
        for (DimensionArgument argument : arguments) {
            formatted = formatter.renderClickable(formatted, argument
                    .dimension().attribute(), TextFormatting
                    .textOf(localizer, argument.dimension().attribute()
                            .currentValue()));
        }
        return formatted;*/
    }

    private String textifyForNegativeArguments(Collection<DimensionArgument> arguments) {
        Iterator<DimensionArgument> iterator = arguments.iterator();

        List<String> argumentValues = new ArrayList<String>();
        while (iterator.hasNext()) {
            DimensionArgument arg = iterator.next();
            argumentValues.add(arg.dimension()
                    .attribute().currentValue().descriptor().toLowerCase(Locale.ENGLISH));
        }
        return TextFormatting.textify(localizer,
                argumentValues);
    }

        private ArrayList<SimpleExplanation> textify(Collection<DimensionArgument> arguments) {
        Iterator<DimensionArgument> iterator = arguments.iterator();

        ArrayList<SimpleExplanation> argumentValues = new ArrayList<SimpleExplanation>();
        while (iterator.hasNext()) {
            DimensionArgument arg = iterator.next();
            SimpleExplanation.IconType iconType = getIcontype(arg) ;
            String explanationText = arg.dimension().attribute().currentValue().getValueName().toLowerCase(Locale.ENGLISH);
            SimpleExplanation oneExplanation = new SimpleExplanation(explanationText,iconType);
            argumentValues.add(oneExplanation);
        }
        return argumentValues;
       // return TextFormatting.textify(localizer,  argumentValues);
    }

    private String chooseRandomOne(String[] texts) {
        return texts[new Random().nextInt(texts.length)];
    }

    private SimpleExplanation.IconType getIcontype (DimensionArgument arg){
        if (arg.dimension().attribute().id().equalsIgnoreCase("color"))return SimpleExplanation.IconType.COLOR;
        else if (arg.dimension().attribute().id().equalsIgnoreCase("price"))return SimpleExplanation.IconType.PRICE;
        else if (arg.dimension().attribute().id().equalsIgnoreCase("label"))return SimpleExplanation.IconType.LABEL;
        else if (arg.dimension().attribute().id().equalsIgnoreCase("clothing-type"))return SimpleExplanation.IconType.TYPE;


        return SimpleExplanation.IconType.COLOR;


    }

}
