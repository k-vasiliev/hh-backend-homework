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
      <span>{{vacancy.description}}</span>
    </div>

    <div class="content-block">
      <h3>Контакты</h3>
      <span>{{vacancy.contacts}}</span>
    </div>

    <div class="content-block">
      <h3>Отклики</h3>
      <template v-if="negotiations.length === 0">
        <span>Откликов нет</span>
      </template>
      <template v-else>
        <p :key="negotiation.id" v-for="negotiation in negotiations">
          {{negotiation.resume.applicant.name}}, <b>{{ negotiation.resume.title }}</b>
        </p>
      </template>
    </div>
  </el-dialog>
</template>

<script>
  import { getVacancy, getNegotiations } from './../api/vacancy'

  export default {
    name: 'ShowVacancyPopup',
    data () {
      return {
        dialogVisible: true,
        dataLoaded: false,
        vacancy: {
          company: {}
        },
        negotiations: []
      }
    },
    methods: {
      open (vacancyId) {
        this.dialogVisible = true
        this.loadVacancyData(vacancyId)
        this.loadNegotiationsData(vacancyId)
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
      },
      loadNegotiationsData (vacancyId) {
        getNegotiations(vacancyId)
          .then(response => {
            this.negotiations = response.data
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
