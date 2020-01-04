package com.jackie.transformplugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class TestPlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        project.extensions.findByType(AppExtension).registerTransform(new TestTransform(project))
    }
}