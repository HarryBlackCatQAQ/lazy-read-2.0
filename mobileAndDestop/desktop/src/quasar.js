import Vue from 'vue'

import './styles/quasar.sass'
//import lang from 'quasar/lang/chinese.js'
import '@quasar/extras/material-icons/material-icons.css'
import '@quasar/extras/ionicons-v4/ionicons-v4.css'
import iconSet from 'quasar/icon-set/fontawesome-v5'
import { 
  Quasar,
  Loading,
  Notify
 } from 'quasar'

Vue.use(Quasar, {
  config: {
    notify: { /* Notify defaults */ }
  },
  iconSet:iconSet,
  components: { /* not needed if importStrategy is not 'manual' */
},
  directives: { /* not needed if importStrategy is not 'manual' */ },
  plugins: {
    Loading,
    Notify
  }
 })