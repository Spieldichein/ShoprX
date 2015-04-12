package de.tum.in.schlichter.shoprx.Explanations.Algorithm;

import de.tum.in.schlichter.shoprx.algorithm.LocalizationModule;

/**
 * Created by Nicksteal on 24.03.2015.
 */
public interface ExplanationLocalizer extends LocalizationModule {
    public String getIndifferentToAnyTemplate();
    public String getOnlySomeTemplate();
    public String getAvoidsSomeTemplate();
    public String getPrefersSomeTemplate();
    public String getCommaString();
    public String getAndString();

    public String[] getStrongArgumentTemplates();
    public String[] getWeakArgumentTemplates();
    public String[] getSerendipitousityTemplates();
    public String[] getContextArgumentTemplates();
    public String getNegativeArgumentTemplate();

    public String getSupportingArgumentTemplate();
    public String getSupportingArgumentTemplateSolo();
        public String getGoodAverageTemplate();
    public String getLastCritiqueTemplate();
}