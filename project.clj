;; This is Leiningen's own project configuration. See doc/TUTORIAL.md
;; file as well as sample.project.clj for help writing your own.

(defproject leiningen "2.7.2-SNAPSHOT"
  :description "Automate Clojure projects without setting your hair on fire."
  :url "https://github.com/technomancy/leiningen"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[leiningen-core "2.7.2-SNAPSHOT"]
                 ;; needed for pom
                 [org.clojure/data.xml "0.0.8"]
                 ;; needed for test
                 [bultitude "0.2.8"]
                 ;; needed for new
                 [stencil "0.5.0" :exclusions [org.clojure/core.cache]]
                 ;; needed for uberjar
                 [commons-lang "2.6"]
                 ;; needed for repl
                 [org.clojure/tools.nrepl "0.2.12"]
                 ;; needed for change
                 [net.cgrand/sjacket "0.1.1" :exclusions [org.clojure/clojure]]
                 ;; bump versions of various common transitive deps
                 [net.cgrand/parsley "0.9.3" :exclusions [org.clojure/clojure]]
                 [scout "0.1.1"]
                 [commons-io "2.5"]]
  ;; checkout-deps don't work with :eval-in :leiningen
  :profiles {:dev {:resource-paths ["leiningen-core/dev-resources"]
                   :test-paths ["leiningen-core/test"]}
             :uberjar {:aot [#"leiningen"
                             leiningen.core.ssl ; lazy-loaded
                             cemerick.pomegranate
                             classlojure.core
                             clojure.tools.nrepl]}}
  :test-selectors {:default (complement :disabled)
                   :offline (comp (partial not-any? identity)
                                  (juxt :online :disabled))}
  :source-paths ["leiningen-core/src" "src"]
  :eval-in :leiningen)
