package com.jpospisil;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

public class RegisterGoToTabsComponent implements ApplicationComponent {
    @Override
    public void initComponent() {
        final ActionManager am = ActionManager.getInstance();
        final DefaultActionGroup windowM = (DefaultActionGroup) am.getAction("EditorTabsGroup");

        final DefaultActionGroup goToTabsGroup = new DefaultActionGroup("Go To Tabs", true);
        addActions(goToTabsGroup, am);

        windowM.add(goToTabsGroup);
    }

    private void addActions(final DefaultActionGroup goToTabsGroup, final ActionManager am) {
        for (int i = 0; i < 10; i++) {
            addAction(i, goToTabsGroup, am);
        }
    }

    private void addAction(final int index, final DefaultActionGroup goToTabGroup,
                           final ActionManager am) {
        final String actionName = "Go To Tab #" + (index + 1);

        final GoToTabAction action = new GoToTabAction(actionName, index);

        goToTabGroup.add(action);
        am.registerAction(actionName, action);
    }

    @Override
    public void disposeComponent() {
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "RegisterGoToTabsComponent";
    }
}
