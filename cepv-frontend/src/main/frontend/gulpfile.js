'use strict';

var gulp = require('gulp');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var minify = require('gulp-minify-css');
var mainBowerFiles = require('main-bower-files');
var templateCache = require('gulp-angular-templatecache');
var plumber = require('gulp-plumber');
var livereload = require('gulp-livereload');

gulp.task('connect', function () {
    var connect = require('connect');
    var proxy = require('proxy-middleware');
    var serveStatic = require('serve-static');
    var url = require('url');
    var open = require('open');

    var app = connect();

    app.use('/src/api', proxy(url.parse('http://localhost:8080/cepv/api')));
    app.use(serveStatic('./'));
    app.listen(8081);

    open('http://localhost:8081/src')
});

gulp.task('application-js', function () {
    return gulp.src('./src/app/**/*.js')
        .pipe(plumber())
        .pipe(concat('scripts.js'))
        .pipe(gulp.dest('./src'))
        .pipe(livereload())
});

gulp.task('templates', function () {
    return gulp.src(['src/app/**/*.html'])
        .pipe(plumber())
        .pipe(templateCache({module: 'cepVisualiser', root: 'app'}))
        .pipe(gulp.dest('src'))
        .pipe(livereload())
});

gulp.task('application-css', function () {
    return gulp.src(['src/assets/css/*.css'])
        .pipe(plumber())
        .pipe(concat('styles.css'))
        .pipe(gulp.dest('./src/css'))
        .pipe(livereload())
});

gulp.task('watch', ['connect'], function () {
    livereload.listen();
    gulp.watch('src/app/**/*.js', ['application-js']);
    gulp.watch('src/assets/css/*.css', ['application-css']);
    gulp.watch('src/app/**/*.html', ['templates']);
});

gulp.task('build-application-js', ['application-js', 'templates'], function () {
    return gulp.src(['./src/scripts.js', './src/templates.js'])
            .pipe(concat('scripts.js'))
            .pipe(uglify())
            .pipe(gulp.dest('./build'));
});

gulp.task('vendor-js', function () {
    return gulp.src(mainBowerFiles('**/*.js'))
            .pipe(concat('vendor.js'))
            .pipe(gulp.dest('./src'));
});

gulp.task('build-js', ['vendor-js', 'build-application-js'], function () {
    return gulp.src('./src/vendor.js')
            .pipe(uglify())
            .pipe(gulp.dest('./build'));
});

gulp.task('vendor-css', function () {
    return gulp.src(mainBowerFiles('**/*.css'))
            .pipe(concat('vendor.css'))
            .pipe(gulp.dest('./src/css'));
});

gulp.task('build-css', ['application-css', 'vendor-css'], function () {
    return gulp.src(['./src/css/*.css'])
            .pipe(minify())
            .pipe(gulp.dest('./build/css'))
});

gulp.task('fonts', function () {
    return gulp.src(mainBowerFiles(['**/components-font-awesome/fonts/*.*','**/bootstrap-css-only/fonts/*.*']))
        .pipe(gulp.dest('./src/fonts'));
});

gulp.task('build-fonts', ['fonts'], function () {
    return gulp.src(['./src/fonts/*'])
            .pipe(gulp.dest('./build/fonts'))
});

gulp.task('build-index', function () {
    return gulp.src('./src/index.html')
            .pipe(gulp.dest('./build'));
});

gulp.task('build', ['build-js', 'build-css', 'build-fonts', 'build-index'], function () {
});

gulp.task('dev', ['build', 'watch'], function () {
});