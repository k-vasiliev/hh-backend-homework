import Vue from 'vue'
import Router from 'vue-router'
import ResumePage from '@/components/ResumePage'
import locale from 'element-ui/lib/locale/lang/ru-RU'

Vue.use(Router, { locale })

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: ResumePage
    }
  ]
})
