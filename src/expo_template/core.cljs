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
(def activity-indicator (r/adapt-react-class (.-ActivityIndicator ReactNative)))
(def Alert (.-Alert ReactNative))
(def button (r/adapt-react-class (.-Button ReactNative)))
(def image (r/adapt-react-class (.-Image ReactNative)))
(def flat-list (r/adapt-react-class (.-FlatList ReactNative)))
(def modal (r/adapt-react-class (.-Modal ReactNative)))
(def picker (r/adapt-react-class (.-Picker ReactNative)))
(def picker-item (r/adapt-react-class (.-Picker.Item ReactNative)))
(def progress-bar (r/adapt-react-class (.-ProgressBarAndroid ReactNative)))
(def safe-area-view (r/adapt-react-class (.-SafeAreaView ReactNative)))
(def scroll-view (r/adapt-react-class (.-ScrollView ReactNative)))
(def slider (r/adapt-react-class (.-Slider ReactNative)))
(def text-input (r/adapt-react-class (.-TextInput ReactNative)))
(def touchable-highlight (r/adapt-react-class (.-TouchableHighlight ReactNative)))


(defn alert [title]
  (.alert Alert title))


(def styles
  {:container {:justify-content "center"
               :margin-top 22
               :margin-bottom 20
               :background-color "#e0e0e0"}
   :horizontal {:flex-direction "row"
                :justify-content "space-around"
                :padding-bottom 10}
   :large-text {:font-size 30
                :font-weight "100"
                :margin-bottom 5
                :text-align "center"}})


(defn section
  [headline-text & children]
  [view
   [text {:style (styles :large-text)} headline-text]
   (into
     [view {:style (styles :horizontal)}]
     children)]) 

(defn app-root
  []
  (let [modal-visible (r/atom false)
        input-box-text (atom "")]
    (fn []
      [view {:style (styles :container)}
       [scroll-view
        [section "Activity Indicator"
         [activity-indicator {:size "large" :color "#0000ff"}]
         [activity-indicator {:size "small" :color "#00ff00"}]
         [activity-indicator {:size "large" :color "#0000ff"}]
         [activity-indicator {:size "small" :color "#00ff00"}]]

        [section "Button"
         [button {:title "Learn More"
                  :color "#841584"
                  :accessibility-label "Learn more about this purple button"
                  :on-press #(alert "Button press 2!")}]]

        [section "Text Input"
         [text-input {:style {:height 30
                              :width 200
                              :border-color "gray"
                              :border-width 1}}]]

        [section "Flat List"
         [flat-list {:data (clj->js ["one" "two" "three"])
                     :key-extractor (fn [_ idx] idx)
                     :render-item (fn [i] (r/as-element [text (.-item i)]))
                     :content-container-style {:justify-content "center"}}]]

        [section "Image"
         [image {:source (js/require "./assets/images/cljs.png")}]]

        [section "Modal"
         [view {:style {:margin-top 22}}
          [modal {:animation-type "slide"
                  :transparent false
                  :visible @modal-visible
                  :on-request-close #(alert "Modal has been closed")}
           [view {:style {:margin-top 22}}
            [view
             [text "Hello!"]
             [touchable-highlight
              {:on-press #(swap! modal-visible not)}
              [text "Hide Modal"]]]]]

          [touchable-highlight
           {:on-press #(reset! modal-visible true)}
           [text "Show modal"]]]]

        [section "Picker"
         (let [picker-value (r/atom "clj")]
           [picker {:selected-value @picker-value
                    :style {:height 100 :width 100}
                    :on-value-change (fn [v _] (reset! picker-value v))}
            [picker-item {:label "clj" :value "clj"}]
            [picker-item {:label "cljs" :value "cljs"}]])]

        [section "Progress Bar"
         [progress-bar]
         [progress-bar {:style-attr "Horizontal"}]
         [progress-bar {:style-attr "Inverse"}]]


        [section "Slider"
         [slider {:style {:width 250}
                  :minimum-value 0,
                  :maximum-value 100 :step 5}]]]])))


(defn init
  []
  (dispatch-sync [:initialize-db])
  (.registerComponent app-registry "main" #(r/reactify-component app-root)))
