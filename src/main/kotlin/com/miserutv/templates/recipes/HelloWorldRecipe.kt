package com.miserutv.templates.recipes

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifestStrings
import com.miserutv.templates.recipes.myresources.res.layout.activityHelloWorldXml
import com.miserutv.templates.recipes.myresources.src.helloWorldActivity

//대화상자에서 Finish 버튼을 누루면 helloWorldActivityRecipe 함수가 기동된다.
fun RecipeExecutor.helloWorldActivityRecipe(
        moduleData: ModuleTemplateData,
        activityClass: String,
        activityTitle: String,
        layoutName: String,
        packageName: String
) {
    val (projectData, srcOut, resOut) = moduleData


    generateManifest(
            moduleData = moduleData,
            activityClass = activityClass,
            activityTitle = activityTitle,
            packageName = packageName,
            isLauncher = false,
            hasNoActionBar = false,
            generateActivityTitle = true,
    )

    save(helloWorldActivity(packageName, activityClass, layoutName), srcOut.resolve("${activityClass}.${projectData.language.extension}"))
    save(activityHelloWorldXml(activityClass), resOut.resolve("layout/${layoutName}.xml"))

    open(resOut.resolve("layout/${layoutName}.xml"))

}