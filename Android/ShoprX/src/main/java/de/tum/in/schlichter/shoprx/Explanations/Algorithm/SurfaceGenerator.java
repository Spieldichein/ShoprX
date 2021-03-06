package de.tum.in.schlichter.shoprx.Explanations.Algorithm;


import android.text.Html;
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
import de.tum.in.schlichter.shoprx.eval.Statistics;

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
        ArrayList<SimpleExplanation> finalSimpleExplanations = new ArrayList<SimpleExplanation>();
        int x =0;
        ArrayList<SimpleExplanation> simpleExplanations = renderDimensionArguments(abstractExplanation);
        if (abstractExplanation.item().isTrendy()&&explanation.getAbstractExplanation().category()!= AbstractExplanation.Category.BY_LAST_CRITIQUE){
            Statistics.get().fashionSuggested();
            finalSimpleExplanations.add(new SimpleExplanation("Suggested by our fashion experts!", SimpleExplanation.IconType.TRENDY));
            x++;
        }
        if (explanation.getSimpleExplanations() != null && explanation.getAbstractExplanation().category()!= AbstractExplanation.Category.BY_LAST_CRITIQUE){
            for (SimpleExplanation oneSimpleExplanation: explanation.getSimpleExplanations()){
                finalSimpleExplanations.add(oneSimpleExplanation);
                x++;
            }
        }



        SimpleExplanation contextArgument = renderContextArguments(abstractExplanation);

        // explanation.addPositiveReason(dimensionArguments);
        if (contextArgument!=null){
            finalSimpleExplanations.add(contextArgument);
            x++;
        }

        for (int i=0; i < simpleExplanations.size();i++){
            if (x < 5){
                finalSimpleExplanations.add(simpleExplanations.get(i));
                x++;
            }else break;
        }
        explanation.setSimpleExplanations(finalSimpleExplanations);


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

                ArrayList<SimpleExplanation> part1 = new ArrayList<SimpleExplanation>();
                        for(DimensionArgument dA: explanation.primaryArguments()){
                            part1.add(render2(chooseRandomOne(localizer.getStrongArgumentTemplates()),dA));
                        }
                if (explanation.hasSupportingArguments())
                    for (DimensionArgument dimArg: explanation.supportingArguments()){
                        part1.add(render2(chooseRandomOne (localizer.getSupportingArgumentTemplateSolo()),dimArg));
                    }

                return part1;

            case BY_WEAK_ARGUMENTS:

                ArrayList<SimpleExplanation> primaryPart = new ArrayList<SimpleExplanation>();

                for(DimensionArgument dA: explanation.primaryArguments()){
                    primaryPart.add(render2(chooseRandomOne(localizer.getWeakArgumentTemplates()),dA));
                }

                if (explanation.hasSupportingArguments()){
                  //  if (primaryPart.size()==0){
                        for (DimensionArgument dimArg: explanation.supportingArguments()){
                            primaryPart.add(render2(chooseRandomOne (localizer.getSupportingArgumentTemplateSolo()),dimArg));
                        }
                  /*  }
                   else {
                        supportingPart = render(
                                localizer.getSupportingArgumentTemplate(),
                                explanation.supportingArguments());
                    }*/
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

    private SimpleExplanation render2 (String template,
                                                DimensionArgument argument) {
        SimpleExplanation textified = textify(argument);
        String text = String.format(template, textified.getText());
        CharSequence formatted = formatter.fromHtml(text);
        textified.setText(formatted.toString());
        return textified;

       /* String text = String.format(template, textify(arguments));
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


    private SimpleExplanation textify(DimensionArgument argument) {
            SimpleExplanation.IconType iconType = getIcontype(argument) ;
            String explanationText = argument.dimension().attribute().currentValue().getValueName().toLowerCase(Locale.ENGLISH);
            SimpleExplanation oneExplanation = new SimpleExplanation(explanationText,iconType);
        return oneExplanation;
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
