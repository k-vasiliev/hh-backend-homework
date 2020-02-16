<template>
  <el-dialog
    :title="vacancy.title"
    :visible.sync="dialogVisible"
    width="30%"
    :before-close="handleClose" v-if="dataLoaded">
    <div class="content-block">
      <h3>Компания</h3>
      <span>{{vacancy.company.name}}</span>
    </div>

    <div class="content-block">
      <h3>Зарплата</h3>
      <span>{{vacancy.salary == null ? 'Не указана' : vacancy.salary}}</span>
    </div>

    <div class="content-block">
      <h3>Описание</h3>
      <span>{{vacancy.desription}}</span>
    </div>

    <div class="content-block">
      <h3>Контакты</h3>
      <span>{{vacancy.contacts}}</span>
    </div>
  </el-dialog>
</template>

<script>
  import { getVacancy } from './../api/vacancy'

  export default {
    name: 'ShowVacancyPopup',
    props: ['vacancyId'],
    data () {
      return {
        dialogVisible: true,
        dataLoaded: false,
        vacancy: {}
      }
    },
    methods: {
      open (vacancyId) {
        this.dialogVisible = true
        this.loadVacancyData(vacancyId)
      },
      handleClose (done) {
        done()
        this.dataLoaded = false
      },
      loadVacancyData (vacancyId) {
        getVacancy(vacancyId)
          .then(response => {
            this.vacancy = response.data
            this.dataLoaded = true
          })
      }
    }
  }
</script>

<style scoped>
  .content-block {
    text-align: left;
    margin-bottom: 25px;
  }

  .content-block:last-child {
    margin-bottom: 0;
  }
</style>
