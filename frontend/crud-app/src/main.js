import Vue from 'vue'
import App from './App.vue'
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import Panel from 'primevue/panel';
import Menubar from 'primevue/menubar';
import Dialog from 'primevue/dialog'
import InputText  from 'primevue/inputtext';
import Button from 'primevue/button';
import ToastService from 'primevue/toastservice';
import Toast from 'primevue/toast';
import Message from 'primevue/message';
import InlineMessage from 'primevue/inlinemessage';
import CascadeSelect from 'primevue/cascadeselect';
import Dropdown from 'primevue/dropdown';
import VueRouter from 'vue-router';
import Form from './components/Form.vue'
import Table from './components/Table.vue'

import 'primevue/resources/themes/saga-blue/theme.css'
import 'primevue/resources/primevue.min.css'
import 'primeicons/primeicons.css'

Vue.config.productionTip = false
Vue.component('DataTable', DataTable);
Vue.component('Column', Column);
Vue.component('Panel', Panel);
Vue.component('Menubar', Menubar);
Vue.component('Dialog', Dialog);
Vue.component('InputText', InputText);
Vue.component('Button', Button);
Vue.component('ToastService', ToastService);
Vue.component('Toast', Toast);
Vue.component('Message', Message);
Vue.component('InlineMessage', InlineMessage);
Vue.component('CascadeSelect', CascadeSelect);
Vue.component('Dropdown', Dropdown);
Vue.component('VueRouter', VueRouter);

Vue.use(VueRouter);

const router = new VueRouter({
  routes: [
    {
      path: '/form',
      component: Form
    },
    {
      path: '/table',
      component: Table
    },
    {
      path:'/',
      redirect:'/form'
    }
  ]
})

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

