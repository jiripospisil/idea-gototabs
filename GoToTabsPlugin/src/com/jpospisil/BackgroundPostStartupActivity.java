package com.jpospisil;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity.Background;
import org.jetbrains.annotations.NotNull;

public class BackgroundPostStartupActivity implements Background {
    @Override
    public void runActivity(@NotNull Project project) {
        final ActionManager am = ActionManager.getInstance();
        final DefaultActionGroup windowM = (DefaultActionGroup) am.getAction("EditorTabsGroup");

        final DefaultActionGroup goToTabsGroup = new DefaultActionGroup("Go To Tabs", true);
        addActions(goToTabsGroup, am);

        windowM.add(goToTabsGroup);
    }

    private void addActions(final DefaultActionGroup goToTabsGroup, final ActionManager am) {
        for (int i = -1; i < 10; i++) {
            addAction(i, goToTabsGroup, am);
        }
    }

    private void addAction(final int index, final DefaultActionGroup goToTabGroup,
                           final ActionManager am) {
        final String actionName = index == -1 ? "Go To Last Tab" : "Go To Tab #" + (index + 1);

        final GoToTabAction action = new GoToTabAction(actionName, index);

        goToTabGroup.add(action);
        am.registerAction(actionName, action);
    }
}
