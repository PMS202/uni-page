package com.github.ethancarter.unipage.web.mvc;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Data
@ConfigurationProperties(prefix = "unipage.web")
public class UniPageProperties {

    private String path;
    private Set<Action> actions;

    @Data
    public static class Action {
        private Set<String> includePages;
        private Set<String> excludePages;
        private String action;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Action that = (Action) o;
            return Objects.equals(action, that.action);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(action);
        }
    }

    public String getPath() {
        return path == null ? "/unipage" : path;
    }

    public Set<Action> getActions() {
        if (CollectionUtils.isEmpty(actions)) {
            // default actions
            actions = new HashSet<>(3);

            Action info = new Action();
            info.setAction("info");
            actions.add(info);

            Action data = new Action();
            data.setAction("data");
            actions.add(data);

            Action exports = new Action();
            exports.setAction("exports");
            return actions;
        }
        return actions;
    }
}
