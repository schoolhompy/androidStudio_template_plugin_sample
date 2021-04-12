package com.miserutv.templates.recipes

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import java.io.File

object HelloWorldTemplate : Template {
    override val category: Category
        get() = Category.Activity     // New 할때, Activity목록,Fragment목록,Other 목록등 나타날 카테고리지정한다.
    override val constraints: Collection<TemplateConstraint>
        get() = emptyList()     //AndroidX, kotlin 사용제약을 할지 결정
    override val description: String
        get() = "My description"     // 설명
    override val documentationUrl: String?
        get() = null          // 설명주소
    override val formFactor: FormFactor
        get() = FormFactor.Mobile //어떤 종류 Mobile,Wear등
    override val minCompileSdk: Int
        get() = MIN_API   // 컴파일 최소 API버젼
    override val minSdk: Int
        get() = MIN_API   // 컴파일 최소 API버젼
    override val name: String
        get() = "Hello Android Template"  // 선택목록에 표시될 템플릿이름
    override val recipe: Recipe
        get() = {                      // 레시피에는 생성하고자할 Acvitiy,Fragment,Layout,Value XMl등을 지정하여 프로젝트폴더에 복사되도록한다.
            helloWorldActivityRecipe(
                    it as ModuleTemplateData,
                    activityClassInputParameter.value,
                    activityTitleInputParameter.value,
                    layoutNameInputParameter.value,
                    packageName.value
            ) // .value 는 화면에서 받은 입력값이다.
        }
    override val revision: Int
        get() = 1  // 템플릿 버젼
    override val uiContexts: Collection<WizardUiContext>
        get() = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule) // 어떤 메뉴등에서 템필릿이 표시될지 지정
    override val widgets: Collection<Widget<*>>
        get() = listOf(
                  TextFieldWidget(activityTitleInputParameter),
                  TextFieldWidget(activityClassInputParameter),
                  TextFieldWidget(layoutNameInputParameter),
                  PackageNameWidget(packageName),
                  LanguageWidget()
            ) // 지금 템플릿을 New로 대화상자가 떴을때 , 필요한 위젯(액티비티이름, 레이아웃이름등)들을 입력할수있도록한다.

    override fun thumb(): Thumb {
        return Thumb { findResource(this.javaClass, File("template_512size.png"))}
    }

    //액티비티 생성이름
   val activityClassInputParameter = stringParameter {
        name = "Activity Name"
        default = "MainActivity"
        help = "The name of the activity class to create"
        constraints = listOf(Constraint.CLASS, Constraint.UNIQUE, Constraint.NONEMPTY)
        suggest = { layoutToActivity(layoutNameInputParameter.value) }
    }
    //레이아웃 생성이름
     var layoutNameInputParameter : StringParameter = stringParameter {
        name = "Layout Name"
        default = "activity_main"
        help = "The name of the layout to create for the activity"
        constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
        suggest = { activityToLayout(activityClassInputParameter.value) }
    }

    //액티비티 생성이름
    val activityTitleInputParameter = stringParameter {
        name = "Title"
        default = "MainActivity"
        help = "The name of the activity. For launcher activities, the application title"
        visible = { false }
        constraints = listOf(Constraint.NONEMPTY)
        suggest = { activityClassInputParameter.value }
    }

    //패키지이름지정
    val packageName = defaultPackageNameParameter
}

val defaultPackageNameParameter get() = stringParameter {
    name = "Package name"
    default = "com.mycompany.myapp"
    constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
    suggest = { packageName }
}