(defproject org.soulspace.clj/cmp.fop "0.3.1"
  :description "The cmp.fop component is a Clojure wrapper for Apache FOP"
  :url "https://github.com/soulspace-org/cmp.fop"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  ; use deps.edn dependencies
  :plugins [[lein-tools-deps "0.4.5"]]
  :middleware [lein-tools-deps.plugin/resolve-dependencies-with-deps-edn]
  :lein-tools-deps/config {:config-files [:install :user :project]}

  :test-paths ["test"]
  :resource-paths ["resources"]
  :scm {:name "git" :url "https://github.com/soulspace-org/cmp.fop"}
  :deploy-repositories [["clojars" {:sign-releases false :url "https://clojars.org/repo"}]])
