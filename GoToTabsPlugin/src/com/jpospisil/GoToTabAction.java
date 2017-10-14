package com.jpospisil;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx;
import com.intellij.openapi.fileEditor.impl.EditorWindow;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindowManager;

public class GoToTabAction extends AnAction {
    private final int index;

    public GoToTabAction(String text, final int index) {
        super(text);
        this.index = index;
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        DataContext dataContext = e.getDataContext();
        Project project = CommonDataKeys.PROJECT.getData(dataContext);
        if (project == null) {
            return;
        }

        ToolWindowManager windowManager = ToolWindowManager.getInstance(project);

        if (windowManager.isEditorComponentActive()) {
            goToTab(dataContext, project);
        }
    }

    private void goToTab(DataContext dataContext, Project project) {
        final FileEditorManagerEx editorManager = FileEditorManagerEx.getInstanceEx(project);
        EditorWindow currentWindow = EditorWindow.DATA_KEY.getData(dataContext);

        if (currentWindow == null) {
            currentWindow = editorManager.getCurrentWindow();
        }

        final VirtualFile[] files = currentWindow.getFiles();
        final int fileIndex = index == -1 ? files.length - 1 : index;
        if (fileIndex >= 0 && fileIndex < files.length) {
            editorManager.openFile(files[fileIndex], true);
        }
    }
}
