<template>
  <v-navigation-drawer>
    <div class="nav-content nav-bar-toggle-icon nav-list">Todo Moleskine</div>
  </v-navigation-drawer>
  <v-list lines="one" :items="items">
    <v-list-item v-for="item in items" :yev="item.id">
      todo id is <a  :href="'http://localhost:3000/api/todos/'+item.id">{{ item.id }}</a> - <span class="align-center">{{ item.title + '  '+ item.updatedAt +'  ' + item.isCompleted}}</span>
      <v-divider></v-divider>
      <v-btn :href="'http://localhost:3000/api/todos/'+item.id" title="press it for edit ToDo entity">
        Edit
      </v-btn>
      <input type="text" v-model="item.title" id="title" value="title" style="background-color: darkgrey">
      <input type="checkbox" v-model="item.isCompleted" :value="false"/>
      {{}}
<!--      <v-date-input v-model="item.updatedAt"></v-date-input>-->
      <v-btn type="submit" value="Submit" style="background-color: #00bfa5" v-on:click="submitForm(item)" text="save"></v-btn>
    </v-list-item>
  </v-list>
</template>
<script>
import axios from 'axios';

export default {
  data() {
    return {
      items: [],
      search: '',
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    submitForm(todoItem) {
      try {
        const response = axios.patch('http://localhost:3000/api/todos', todoItem);
        console.log("patch called successfully");
        console.log(response);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
      alert('Submitted todo with id : '+todoItem.id)
    },
    async fetchData() {
      try {
        const response = await axios.get('http://localhost:3000/api/todos/');
        this.items = response.data; // Assuming response data is an array
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    },
  },
};
</script>
