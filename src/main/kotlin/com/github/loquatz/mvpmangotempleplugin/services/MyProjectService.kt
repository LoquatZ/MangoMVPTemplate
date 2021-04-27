package com.github.loquatz.mvpmangotempleplugin.services

import com.github.loquatz.mvpmangotempleplugin.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
