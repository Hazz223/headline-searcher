
function Article(data){
    this.id = ko.observable(data.id);
    this.title = ko.observable(data.title);
    this.description = ko.observable(data.description);
    this.link = ko.observable(data.link);
    this.image = ko.observable(data.image);
}

function Page(data){
    console.log(data.totalElements);
    this.total = ko.observable(data.totalElements);
}

function IndexViewModel() {
    var self = this;
    self.articles = ko.observable();
    self.page = ko.observable();
    self.searchBox = ko.observable();

    this.throttledValue = ko.computed(this.searchBox)
                            .extend({ throttle: 400 });

    $.getJSON("/article", function(data) {

        var mappedTasks = $.map(
                data.content,
                function(test) {
                    return new Article(test);
                });


        self.page(new Page(data));

        self.articles(mappedTasks);
    });

    this.throttledValue.subscribe(function(val){
        $.getJSON("/article?searchTerm=" + val, function(data) {

            var mappedTasks = $.map(
                    data.content,
                    function(test) {
                        return new Article(test);
                    });

            self.page(new Page(data));
            self.articles(mappedTasks);
        });
    });
}

ko.applyBindings(new IndexViewModel());