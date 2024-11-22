/**
 * main.ts
 *
 * Bootstraps Vuetify and other plugins then mounts the App`
 */

// Plugins
import { registerPlugins } from '@/plugins'

// Components
import App from './App.vue'

// Composables
import { createApp } from 'vue'

import { ApmVuePlugin } from '@elastic/apm-rum-vue'

const app = createApp(App).use(ApmVuePlugin, {
  config: {
    serviceName: 'moleskine-front-web',
    // agent configuration
  }
})

registerPlugins(app)

app.mount('#app')
