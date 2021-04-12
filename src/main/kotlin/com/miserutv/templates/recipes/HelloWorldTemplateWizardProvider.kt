package com.miserutv.templates.recipes

import com.android.tools.idea.wizard.template.WizardTemplateProvider

class HelloWorldTemplateWizardProvider: WizardTemplateProvider() {
    override fun getTemplates() = listOf(HelloWorldTemplate)
}