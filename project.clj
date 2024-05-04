(defproject org.soulspace.clj/cmp.fop "0.3.3"
  :description "The cmp.fop component is a Clojure wrapper for Apache FOP"
  :url "https://github.com/soulspace-org/cmp.fop"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  ; use deps.edn dependencies
  ;:plugins [[lein-tools-deps "0.4.5"]]
  ;:middleware [lein-tools-deps.plugin/resolve-dependencies-with-deps-edn]
  ;:lein-tools-deps/config {:config-files [:install :user :project]}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [commons-logging "1.2"]
                 [org.apache.xmlgraphics/fop "2.9" :exclusions [com.sun.media/jai-codec javax.media/jai-codec javax.media/jai-core]]
                 [org.soulspace.clj/clj.java "0.9.0"]]

  :test-paths ["test"]
  :resource-paths ["resources"]
  :scm {:name "git" :url "https://github.com/soulspace-org/cmp.fop"}
  :deploy-repositories [["clojars" {:sign-releases false :url "https://clojars.org/repo"}]])
