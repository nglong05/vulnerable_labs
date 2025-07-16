package com.example.freemarker_ssti_lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.DefaultObjectWrapper;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;

import jakarta.servlet.http.HttpServletResponse;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TemplateController {

	@Autowired
	private Configuration freemarkerConfig;

	@GetMapping("/")
	public String form() {
		return "form";
	}

	@PostMapping("/render")
	public void render(@RequestParam("payload") String payload, HttpServletResponse resp) throws Exception {
		Template t = new Template("user", new StringReader(payload), freemarkerConfig);
		StringWriter out = new StringWriter();

		Map<String, Object> model = new HashMap<>();
		//BeansWrapper wrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_34).build();
		//model.put("product", this);		
		//model.put("statics", wrapper.getStaticModels());
		
		
		t.process(model, out);

		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().print(out.toString());
	}
}

