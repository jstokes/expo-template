(ns expo-template.core
  (:require
    [expo-template.handlers]
    [expo-template.subs]
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [reagent.core :as r :refer [atom]]))


(def ReactNative (js/require "react-native"))
(def app-registry (.-AppRegistry ReactNative))
(def text (r/adapt-react-class (.-Text ReactNative)))
(def view (r/adapt-react-class (.-View ReactNative)))
(def image (r/adapt-react-class (.-Image ReactNative)))

(defn app-root
  []
  (fn []
    [view {:style {:flex-direction "column" :margin 40 :align-items "center"}}
     [text {:style {:font-size 30
                    :font-weight "100"
                    :margin-bottom 20
                    :text-align "center"}}
      "new old world"]]))


(defn init
  []
  (dispatch-sync [:initialize-db])
  (.registerComponent app-registry "main" #(r/reactify-component app-root)))
